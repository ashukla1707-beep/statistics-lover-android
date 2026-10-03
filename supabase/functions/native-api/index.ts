import "jsr:@supabase/functions-js/edge-runtime.d.ts";
import { createClient } from "npm:@supabase/supabase-js@2";

const SUPABASE_URL = Deno.env.get("SUPABASE_URL")!;
const ANON_KEY = Deno.env.get("SUPABASE_ANON_KEY")!;

const json = (body: unknown, status = 200) =>
  new Response(JSON.stringify(body), {
    status,
    headers: { "content-type": "application/json; charset=utf-8" },
  });

const publicClient = () =>
  createClient(SUPABASE_URL, ANON_KEY, {
    auth: { persistSession: false, autoRefreshToken: false },
  });

const userClient = (token: string) =>
  createClient(SUPABASE_URL, ANON_KEY, {
    auth: { persistSession: false, autoRefreshToken: false },
    global: { headers: { Authorization: `Bearer ${token}` } },
  });

function requireToken(body: Record<string, unknown>) {
  const token = typeof body.accessToken === "string" ? body.accessToken : "";
  if (!token) throw new Error("Authentication required.");
  return token;
}

async function bootstrap(token: string) {
  const client = userClient(token);
  const { data: userData, error: userError } = await client.auth.getUser(token);
  if (userError || !userData.user) throw userError ?? new Error("Invalid session.");
  const user = userData.user;

  const [profileResult, rolesResult, enrollmentsResult, summaryResult, ordersResult] =
    await Promise.all([
      client.from("profiles")
        .select("id,full_name,phone,avatar_url,account_status")
        .eq("id", user.id)
        .maybeSingle(),
      client.from("user_roles").select("role").eq("user_id", user.id),
      client.from("enrollments").select(`
        id,status,enrolled_at,access_ends_at,
        batch:batches!enrollments_batch_id_fkey(
          id,title,slug,status,starts_on,ends_on,
          course:courses!batches_course_id_fkey(id,title,slug,thumbnail_url)
        )
      `).eq("student_id", user.id)
        .in("status", ["active", "completed"])
        .order("enrolled_at", { ascending: false }),
      client.rpc("get_my_notification_summary"),
      client.from("commerce_orders").select(`
        id,order_number,batch_id,currency,subtotal_minor,discount_minor,total_minor,coupon_code,status,provider,
        provider_payment_reference,expires_at,paid_at,created_at,
        batch:batches!commerce_orders_batch_id_fkey(title,course:courses!batches_course_id_fkey(title)),
        receipt:commerce_receipts!commerce_receipts_order_id_fkey(receipt_number,issued_at)
      `).eq("student_id", user.id).order("created_at", { ascending: false }),
    ]);

  for (const result of [profileResult, rolesResult, enrollmentsResult, summaryResult, ordersResult]) {
    if (result.error) throw result.error;
  }

  const roles = (rolesResult.data ?? []).map((row: { role: string }) => row.role);
  let teacherAssignments: unknown[] = [];
  if (roles.includes("teacher")) {
    const assignmentResult = await client.from("teacher_assignments")
      .select("id,batch_id,subject_id,starts_at,ends_at,is_active")
      .eq("teacher_id", user.id)
      .eq("is_active", true)
      .order("created_at");
    if (assignmentResult.error) throw assignmentResult.error;
    teacherAssignments = assignmentResult.data ?? [];
  }

  return {
    user: { id: user.id, email: user.email ?? null },
    profile: profileResult.data,
    roles,
    enrollments: enrollmentsResult.data ?? [],
    notificationSummary: (summaryResult.data ?? [])[0] ?? { unread_count: 0, total_count: 0 },
    orders: ordersResult.data ?? [],
    teacherAssignments,
  };
}

Deno.serve(async (req) => {
  if (req.method !== "POST") return json({ error: "Method not allowed" }, 405);

  try {
    const body = await req.json() as Record<string, unknown>;
    const action = typeof body.action === "string" ? body.action : "";

    if (action === "signIn") {
      const email = String(body.email ?? "").trim();
      const password = String(body.password ?? "");
      const { data, error } = await publicClient().auth.signInWithPassword({ email, password });
      if (error) throw error;
      return json({
        accessToken: data.session?.access_token ?? null,
        refreshToken: data.session?.refresh_token ?? null,
        userId: data.user?.id ?? null,
        email: data.user?.email ?? null,
      });
    }

    if (action === "signUp") {
      const email = String(body.email ?? "").trim();
      const password = String(body.password ?? "");
      const fullName = String(body.fullName ?? "").trim();
      const phone = String(body.phone ?? "").trim();
      const { data, error } = await publicClient().auth.signUp({
        email,
        password,
        options: { data: { full_name: fullName, phone: phone || null } },
      });
      if (error) throw error;
      return json({
        accessToken: data.session?.access_token ?? null,
        refreshToken: data.session?.refresh_token ?? null,
        userId: data.user?.id ?? null,
        email: data.user?.email ?? null,
        confirmationRequired: !data.session,
      });
    }

    if (action === "recover") {
      const email = String(body.email ?? "").trim();
      const { error } = await publicClient().auth.resetPasswordForEmail(email, {
        redirectTo: "https://statistics-lover.vercel.app/reset-password",
      });
      if (error) throw error;
      return json({ ok: true });
    }

    if (action === "refresh") {
      const refreshToken = String(body.refreshToken ?? "");
      const { data, error } = await publicClient().auth.refreshSession({ refresh_token: refreshToken });
      if (error) throw error;
      return json({
        accessToken: data.session?.access_token ?? null,
        refreshToken: data.session?.refresh_token ?? null,
        userId: data.user?.id ?? null,
        email: data.user?.email ?? null,
      });
    }

    if (action === "bootstrap") {
      return json(await bootstrap(requireToken(body)));
    }

    if (action === "notifications") {
      const client = userClient(requireToken(body));
      const { data, error } = await client.from("in_app_notifications")
        .select("id,kind,title,body,action_url,available_at,expires_at,read_at,created_at")
        .order("available_at", { ascending: false })
        .limit(100);
      if (error) throw error;
      return json({ notifications: data ?? [] });
    }

    if (action === "markAllRead") {
      const client = userClient(requireToken(body));
      const { error } = await client.rpc("mark_all_notifications_read");
      if (error) throw error;
      return json({ ok: true });
    }

    if (action === "orders") {
      const client = userClient(requireToken(body));
      const { data: userData, error: userError } = await client.auth.getUser(requireToken(body));
      if (userError || !userData.user) throw userError ?? new Error("Invalid session.");
      const { data, error } = await client.from("commerce_orders").select(`
        id,order_number,batch_id,currency,subtotal_minor,discount_minor,total_minor,coupon_code,status,provider,
        provider_payment_reference,expires_at,paid_at,created_at,
        batch:batches!commerce_orders_batch_id_fkey(title,course:courses!batches_course_id_fkey(title)),
        receipt:commerce_receipts!commerce_receipts_order_id_fkey(receipt_number,issued_at)
      `).eq("student_id", userData.user.id).order("created_at", { ascending: false });
      if (error) throw error;
      return json({ orders: data ?? [] });
    }

    if (action === "offers") {
      const { data, error } = await publicClient().rpc("get_public_batch_offers");
      if (error) throw error;
      return json({ offers: data ?? [] });
    }

    if (action === "createOrder") {
      const client = userClient(requireToken(body));
      const { data, error } = await client.rpc("create_commerce_order", {
        target_batch: String(body.batchId ?? ""),
        coupon_code: String(body.couponCode ?? "").trim() || null,
        payment_provider: "manual",
      });
      if (error) throw error;
      return json({ orderId: data });
    }

    if (action === "learning") {
      const token = requireToken(body);
      const client = userClient(token);
      const batchId = String(body.batchId ?? "");

      const { data: subjects, error: subjectError } = await client.from("subjects")
        .select("id,title,code,description,position")
        .eq("batch_id", batchId).eq("status", "published")
        .order("position").order("title");
      if (subjectError) throw subjectError;

      const subjectIds = (subjects ?? []).map((row: { id: string }) => row.id);
      let modules: unknown[] = [];
      let lectures: unknown[] = [];
      if (subjectIds.length) {
        const moduleResult = await client.from("modules")
          .select("id,subject_id,title,description,position")
          .in("subject_id", subjectIds).eq("status", "published")
          .order("position").order("title");
        if (moduleResult.error) throw moduleResult.error;
        modules = moduleResult.data ?? [];

        const moduleIds = (modules as Array<{ id: string }>).map((row) => row.id);
        if (moduleIds.length) {
          const lectureResult = await client.from("lectures")
            .select("id,module_id,title,description,status,delivery_mode,position,scheduled_at,duration_minutes,release_at")
            .in("module_id", moduleIds)
            .in("status", ["scheduled", "live", "published"])
            .order("position").order("title");
          if (lectureResult.error) throw lectureResult.error;
          lectures = lectureResult.data ?? [];
        }
      }

      const actionResult = await client.rpc("get_batch_delivery_actions", { target_batch: batchId });
      if (actionResult.error) throw actionResult.error;

      return json({
        subjects: subjects ?? [],
        modules,
        lectures,
        actions: actionResult.data ?? [],
      });
    }

    if (action === "operationsCourses") {
      const client = userClient(requireToken(body));
      const { data, error } = await client.from("courses")
        .select("id,title,slug,status,description").order("title");
      if (error) throw error;
      return json({ courses: data ?? [] });
    }

    return json({ error: "Unknown action" }, 400);
  } catch (error) {
    const message = error instanceof Error ? error.message : "Request failed";
    return json({ error: message }, 400);
  }
});
