package com.statisticslover.app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class NativeMainActivity extends AppCompatActivity {
    private final ExecutorService io=Executors.newSingleThreadExecutor();
    private final Set<String> roles=new HashSet<>();

    private NativeApiClient api;
    private NativeUi ui;
    private LinearLayout root;
    private FrameLayout content;
    private String screen="public-home";

    private JSONObject profile=new JSONObject();
    private JSONObject summary=new JSONObject();
    private JSONArray enrollments=new JSONArray();
    private JSONArray orders=new JSONArray();
    private JSONArray teacherAssignments=new JSONArray();

    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        api=new NativeApiClient(this);
        ui=new NativeUi(this);
        createRoot();
        configureBack();
        showPublicHome();
    }

    private void createRoot(){
        root=new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(NativeUi.BG);
        ViewCompat.setOnApplyWindowInsetsListener(root,(v,i)->{
            Insets b=i.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(b.left,b.top,b.right,b.bottom);
            return i;
        });
        content=new FrameLayout(this);
        root.addView(content,new LinearLayout.LayoutParams(-1,0,1f));
        setContentView(root);
    }

    private void configureBack(){
        getOnBackPressedDispatcher().addCallback(this,new OnBackPressedCallback(true){
            @Override public void handleOnBackPressed(){
                if("public-home".equals(screen)){
                    finish();
                }else if("login".equals(screen)||"dashboard".equals(screen)){
                    showPublicHome();
                }else if(profile.length()>0){
                    buildChrome();
                    showHome();
                }else{
                    showPublicHome();
                }
            }
        });
    }

    private void replace(View view){
        content.removeAllViews();
        content.addView(view,new FrameLayout.LayoutParams(-1,-1));
    }

    private void busy(String message){
        screen="busy";
        LinearLayout box=new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.addView(new ProgressBar(this));
        ui.add(box,ui.text(message,14,NativeUi.MUTED,false),12);
        replace(box);
    }

    private void showLogin(){
        screen="login";
        buildPublicChrome();
        replace(LoginScreen.build(this,ui,(email,credential)->{
            if(email.isBlank()||credential.isBlank()){
                toast("Enter email and password.");
                return;
            }
            busy("Signing in…");
            io.execute(()->{
                try{
                    api.signIn(email,credential);
                    runOnUiThread(this::bootstrap);
                }catch(Exception error){
                    runOnUiThread(()->{
                        showLogin();
                        toast(message(error));
                    });
                }
            });
        }));
    }

    private void bootstrap(){
        busy("Loading your account…");
        io.execute(()->{
            try{
                JSONObject data=api.bootstrap();
                profile=data.optJSONObject("profile");
                if(profile==null||!"active".equals(profile.optString("account_status"))){
                    throw new Exception("This account is not active.");
                }

                roles.clear();
                JSONArray roleRows=array(data,"roles");
                for(int i=0;i<roleRows.length();i++) roles.add(roleRows.optString(i));

                enrollments=array(data,"enrollments");
                orders=array(data,"orders");
                teacherAssignments=array(data,"teacherAssignments");
                summary=data.optJSONObject("notificationSummary");
                if(summary==null)summary=new JSONObject();

                runOnUiThread(()->{
                    buildChrome();
                    showHome();
                });
            }catch(Exception error){
                runOnUiThread(()->{
                    api.signOut();
                    showLogin();
                    toast(message(error));
                });
            }
        });
    }

    private LinearLayout siteHeader(boolean accountMode){
        LinearLayout top=new LinearLayout(this);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.setPadding(ui.dp(18),ui.dp(9),ui.dp(8),ui.dp(9));
        top.setBackgroundColor(Color.WHITE);

        LinearLayout brand=new LinearLayout(this);
        brand.setOrientation(LinearLayout.VERTICAL);
        brand.addView(ui.text("Statistics Lover",19,NativeUi.NAVY,true));
        brand.addView(ui.text("Learn • Practice • Succeed",10,NativeUi.MUTED,true));
        top.addView(brand,new LinearLayout.LayoutParams(0,-2,1f));

        Button menu=ui.button("☰",false);
        menu.setTextSize(22);
        menu.setBackgroundColor(Color.TRANSPARENT);
        menu.setContentDescription("Open navigation");
        menu.setOnClickListener(v->{
            if(accountMode) showAccountMenu(v);
            else showPublicMenu(v);
        });
        top.addView(menu,new LinearLayout.LayoutParams(ui.dp(56),ui.dp(48)));
        return top;
    }

    private void buildPublicChrome(){
        root.removeAllViews();
        root.addView(siteHeader(false));
        content=new FrameLayout(this);
        root.addView(content,new LinearLayout.LayoutParams(-1,0,1f));
    }

    private void buildChrome(){
        root.removeAllViews();
        root.addView(siteHeader(true));
        content=new FrameLayout(this);
        root.addView(content,new LinearLayout.LayoutParams(-1,0,1f));
    }

    private void showPublicMenu(View anchor){
        PopupMenu popup=new PopupMenu(this,anchor);
        popup.getMenu().add("Home");
        popup.getMenu().add("Courses");
        popup.getMenu().add("Free Content");
        popup.getMenu().add("Test Series");
        popup.getMenu().add("PYQs");
        popup.getMenu().add("Study Material");
        popup.getMenu().add("About");
        popup.getMenu().add(api.session().hasSession()?"Dashboard":"Student Login");
        popup.setOnMenuItemClickListener(item->{
            String title=item.getTitle().toString();
            switch(title){
                case "Home" -> showPublicHome();
                case "Courses" -> showStore();
                case "Free Content" -> showPublicSection(
                        "Free Content",
                        "Open lessons and learning resources are part of the Statistics Lover ecosystem."
                );
                case "Test Series" -> showPublicSection(
                        "Test Series",
                        "Topic-wise and full-length assessments are connected to your Statistics Lover account."
                );
                case "PYQs" -> showPublicSection(
                        "PYQs",
                        "Previous-year questions are organized with tests and performance analytics."
                );
                case "Study Material" -> showPublicSection(
                        "Study Material",
                        "Notes, formula sheets and learning resources are available through enrolled courses."
                );
                case "About" -> showPublicSection(
                        "About Statistics Lover",
                        "A focused platform for live classes, recorded lectures, tests, PYQs and study material."
                );
                default -> openAccount();
            }
            return true;
        });
        popup.show();
    }

    private void showAccountMenu(View anchor){
        PopupMenu popup=new PopupMenu(this,anchor);
        popup.getMenu().add("Home");
        popup.getMenu().add("Dashboard");
        popup.getMenu().add("My Courses");
        popup.getMenu().add("Notifications");
        popup.getMenu().add("My Orders");
        popup.getMenu().add("Courses");
        if(roles.contains("teacher")) popup.getMenu().add("Teacher");
        if(hasOperationsRole()) popup.getMenu().add("Admin");
        popup.getMenu().add("Logout");
        popup.setOnMenuItemClickListener(item->{
            String title=item.getTitle().toString();
            switch(title){
                case "Home" -> showPublicHome();
                case "Dashboard" -> showHome();
                case "My Courses" -> showCourses();
                case "Notifications" -> showInbox();
                case "My Orders" -> showOrders();
                case "Courses" -> showStore();
                case "Teacher" -> showTeacher();
                case "Admin" -> showOperations();
                case "Logout" -> {
                    api.signOut();
                    clearAccountState();
                    showPublicHome();
                }
            }
            return true;
        });
        popup.show();
    }

    private void openAccount(){
        if(!api.session().hasSession()){
            showLogin();
            return;
        }
        if(profile.length()>0){
            buildChrome();
            showHome();
        }else{
            bootstrap();
        }
    }

    private void showPublicHome(){
        buildPublicChrome();
        screen="public-home";
        replace(PublicHomeScreen.build(ui,new PublicHomeScreen.Listener(){
            @Override public void onExploreCourses(){
                showStore();
            }

            @Override public void onFreeContent(){
                showPublicSection(
                        "Free Content",
                        "Explore open learning resources and then sign in when you are ready to continue with a batch."
                );
            }

            @Override public void onAccount(){
                openAccount();
            }
        },api.session().hasSession()));
    }

    private void showPublicSection(String title,String description){
        buildPublicChrome();
        screen="public-section";
        ScrollView scroll=ui.page(title,description);
        LinearLayout body=ui.body(scroll);

        LinearLayout card=ui.card();
        card.addView(ui.text("Statistics Lover",12,NativeUi.MAGENTA,true));
        card.addView(ui.text(
                "The same learning ecosystem is available across the web platform and Android app.",
                16,NativeUi.NAVY,true
        ));
        ui.add(card,ui.text(
                "Use Courses to browse available batches or Student Login to continue with your account.",
                13,NativeUi.MUTED,false
        ),8);
        body.addView(card);

        Button courses=ui.button("Explore Courses",true);
        courses.setOnClickListener(v->showStore());
        ui.add(body,courses,6);

        Button back=ui.button("Back to Home",false);
        back.setOnClickListener(v->showPublicHome());
        ui.add(body,back,10);

        replace(scroll);
    }

    private void clearAccountState(){
        roles.clear();
        profile=new JSONObject();
        summary=new JSONObject();
        enrollments=new JSONArray();
        orders=new JSONArray();
        teacherAssignments=new JSONArray();
    }

    private void showHome(){
        screen="dashboard";
        String name=profile.optString("full_name","");
        if(name.isBlank()||"null".equals(name)) name=api.session().email();

        ScrollView scroll=ui.page("Welcome, "+name,"Your Statistics Lover dashboard");
        LinearLayout body=ui.body(scroll);

        LinearLayout role=ui.card();
        role.addView(ui.text("Roles",12,NativeUi.MAGENTA,true));
        role.addView(ui.text(roles.isEmpty()?"student":String.join(" • ",roles),15,NativeUi.NAVY,true));
        body.addView(role);

        metric(body,"Enrolled batches",String.valueOf(enrollments.length()),"Open Courses to study");
        metric(body,"Unread notifications",String.valueOf(summary.optInt("unread_count",0)),"Open Inbox to review");
        metric(body,"Orders",String.valueOf(orders.length()),"Payment and receipt status");
        if(roles.contains("teacher")){
            metric(body,"Teaching assignments",String.valueOf(teacherAssignments.length()),"Active teaching scope");
        }

        Button coursesButton=ui.button("Open my courses",true);
        coursesButton.setOnClickListener(v->showCourses());
        ui.add(body,coursesButton,4);

        Button storeButton=ui.button("Browse course store",false);
        storeButton.setOnClickListener(v->showStore());
        ui.add(body,storeButton,10);

        replace(scroll);
    }

    private void metric(LinearLayout body,String label,String value,String note){
        LinearLayout card=ui.card();
        card.addView(ui.text(label,12,NativeUi.MUTED,true));
        card.addView(ui.text(value,28,NativeUi.NAVY,true));
        card.addView(ui.text(note,12,NativeUi.MUTED,false));
        body.addView(card);
    }

    private void showCourses(){
        screen="courses";
        ScrollView scroll=ui.page("My courses","Your enrolled batches");
        LinearLayout body=ui.body(scroll);

        if(enrollments.length()==0){
            body.addView(ui.text("No active enrollment yet.",15,NativeUi.MUTED,false));
        }

        for(int i=0;i<enrollments.length();i++){
            JSONObject row=enrollments.optJSONObject(i);
            JSONObject batch=row==null?null:row.optJSONObject("batch");
            JSONObject course=batch==null?null:batch.optJSONObject("course");
            if(batch==null||course==null)continue;

            LinearLayout card=ui.card();
            card.addView(ui.text(course.optString("title","Course"),18,NativeUi.NAVY,true));
            card.addView(ui.text(batch.optString("title","Batch"),13,NativeUi.MUTED,false));
            card.addView(ui.text(row.optString("status",""),11,NativeUi.MAGENTA,true));

            String batchId=batch.optString("id");
            String courseTitle=course.optString("title","Course");
            String batchTitle=batch.optString("title","Batch");
            Button open=ui.button("Open learning",true);
            open.setOnClickListener(v->showLearning(batchId,courseTitle,batchTitle));
            ui.add(card,open,10);
            body.addView(card);
        }

        replace(scroll);
    }

    private void showLearning(String batchId,String courseTitle,String batchTitle){
        busy("Loading "+batchTitle+"…");
        io.execute(()->{
            try{
                JSONObject data=api.learning(batchId);
                runOnUiThread(()->replace(
                        LearningScreen.build(
                                this,ui,courseTitle,batchTitle,data,
                                this::showCourses,
                                this::openExternal
                        )
                ));
                screen="learning";
            }catch(Exception error){
                runOnUiThread(()->{
                    showCourses();
                    toast(message(error));
                });
            }
        });
    }

    private void showInbox(){
        busy("Loading inbox…");
        io.execute(()->{
            try{
                JSONObject data=api.notifications();
                JSONArray rows=array(data,"notifications");
                runOnUiThread(()->renderInbox(rows));
            }catch(Exception error){
                runOnUiThread(()->{
                    showHome();
                    toast(message(error));
                });
            }
        });
    }

    private void renderInbox(JSONArray rows){
        screen="inbox";
        ScrollView scroll=ui.page("Notifications","Announcements and account updates");
        LinearLayout body=ui.body(scroll);

        Button mark=ui.button("Mark all read",false);
        mark.setOnClickListener(v->{
            busy("Updating inbox…");
            io.execute(()->{
                try{
                    api.markAllRead();
                    JSONObject fresh=api.notifications();
                    runOnUiThread(()->renderInbox(array(fresh,"notifications")));
                }catch(Exception error){
                    runOnUiThread(()->toast(message(error)));
                }
            });
        });
        body.addView(mark);

        if(rows.length()==0)ui.add(body,ui.text("You are all caught up.",15,NativeUi.MUTED,false),12);

        for(int i=0;i<rows.length();i++){
            JSONObject row=rows.optJSONObject(i);
            if(row==null)continue;
            LinearLayout card=ui.card();
            card.addView(ui.text(row.optString("title","Notification"),16,NativeUi.NAVY,true));
            card.addView(ui.text(row.optString("body",""),13,NativeUi.MUTED,false));
            body.addView(card);
        }

        replace(scroll);
    }

    private void showOrders(){
        busy("Loading orders…");
        io.execute(()->{
            try{
                JSONObject data=api.orders();
                orders=array(data,"orders");
                runOnUiThread(()->renderOrders(orders));
            }catch(Exception error){
                runOnUiThread(()->{
                    showHome();
                    toast(message(error));
                });
            }
        });
    }

    private void renderOrders(JSONArray rows){
        screen="orders";
        ScrollView scroll=ui.page("My orders","Payment verification and receipts");
        LinearLayout body=ui.body(scroll);
        NumberFormat money=NumberFormat.getCurrencyInstance(new Locale("en","IN"));

        if(rows.length()==0)body.addView(ui.text("No orders yet.",15,NativeUi.MUTED,false));

        for(int i=0;i<rows.length();i++){
            JSONObject row=rows.optJSONObject(i);
            if(row==null)continue;
            JSONObject batch=row.optJSONObject("batch");
            JSONObject course=batch==null?null:batch.optJSONObject("course");

            LinearLayout card=ui.card();
            card.addView(ui.text(row.optString("order_number","Order"),12,NativeUi.MAGENTA,true));
            card.addView(ui.text(course==null?"Course":course.optString("title","Course"),17,NativeUi.NAVY,true));
            card.addView(ui.text(
                    money.format(row.optLong("total_minor",0)/100.0)+" • "+row.optString("status",""),
                    13,
                    "paid".equals(row.optString("status"))?NativeUi.GREEN:NativeUi.MUTED,
                    true
            ));
            body.addView(card);
        }

        replace(scroll);
    }

    private void showStore(){
        boolean accountReady=profile.length()>0;
        busy("Loading courses…");
        io.execute(()->{
            try{
                JSONObject data=api.offers();
                JSONArray offers=array(data,"offers");
                runOnUiThread(()->replace(
                        StoreScreen.build(
                                this,ui,offers,
                                (batchId,coupon)->{
                                    if(!api.session().hasSession()){
                                        toast("Sign in to create an order.");
                                        showLogin();
                                    }else{
                                        createOrder(batchId,coupon);
                                    }
                                },
                                accountReady?this::showHome:this::showPublicHome
                        )
                ));
                screen="store";
            }catch(Exception error){
                runOnUiThread(()->{
                    if(accountReady) showHome(); else showPublicHome();
                    toast(message(error));
                });
            }
        });
    }

    private void createOrder(String batchId,String coupon){
        busy("Creating order…");
        io.execute(()->{
            try{
                api.createOrder(batchId,coupon);
                JSONObject data=api.orders();
                orders=array(data,"orders");
                runOnUiThread(()->{
                    toast("Order created.");
                    renderOrders(orders);
                });
            }catch(Exception error){
                runOnUiThread(()->{
                    showStore();
                    toast(message(error));
                });
            }
        });
    }

    private void showTeacher(){
        screen="teacher";
        ScrollView scroll=ui.page("Teacher workspace","Your active teaching scope");
        LinearLayout body=ui.body(scroll);

        if(teacherAssignments.length()==0){
            body.addView(ui.text("No active teaching assignment.",15,NativeUi.MUTED,false));
        }

        for(int i=0;i<teacherAssignments.length();i++){
            JSONObject row=teacherAssignments.optJSONObject(i);
            if(row==null)continue;
            LinearLayout card=ui.card();
            card.addView(ui.text(
                    row.isNull("subject_id")?"Whole-batch scope":"Subject scope",
                    12,NativeUi.MAGENTA,true));
            card.addView(ui.text("Batch: "+row.optString("batch_id",""),13,NativeUi.NAVY,false));
            if(!row.isNull("subject_id")){
                card.addView(ui.text("Subject: "+row.optString("subject_id",""),13,NativeUi.NAVY,false));
            }
            body.addView(card);
        }

        LinearLayout note=ui.card();
        note.addView(ui.text("Native teaching modules",16,NativeUi.NAVY,true));
        note.addView(ui.text(
                "Attendance, grading, tests and content-management workflows are the next native Android layer.",
                12,NativeUi.MUTED,false));
        body.addView(note);

        replace(scroll);
    }

    private void showOperations(){
        busy("Loading operations…");
        io.execute(()->{
            try{
                JSONObject data=api.operationsCourses();
                JSONArray courses=array(data,"courses");
                runOnUiThread(()->renderOperations(courses));
            }catch(Exception error){
                runOnUiThread(()->{
                    showHome();
                    toast(message(error));
                });
            }
        });
    }

    private void renderOperations(JSONArray courses){
        screen="operations";
        ScrollView scroll=ui.page("Operations","Role-aware native workspace");
        LinearLayout body=ui.body(scroll);

        LinearLayout access=ui.card();
        access.addView(ui.text("Access",12,NativeUi.MAGENTA,true));
        access.addView(ui.text(String.join(" • ",roles),15,NativeUi.NAVY,true));
        access.addView(ui.text(courses.length()+" courses visible in your scope",12,NativeUi.MUTED,false));
        body.addView(access);

        String[] modules=(roles.contains("admin")||roles.contains("owner"))
                ? new String[]{"Academics","Content","Enrollments","Assignments","Attendance","Tests","Announcements","Commerce","Staff","Audit","Settings"}
                : new String[]{"Academics","Content","Assignments","Attendance","Tests","Announcements"};

        for(String module:modules){
            LinearLayout card=ui.card();
            card.addView(ui.text(module,16,NativeUi.NAVY,true));
            card.addView(ui.text("Native management workflow will be added in the next Android layer.",12,NativeUi.MUTED,false));
            body.addView(card);
        }

        replace(scroll);
    }

    private boolean hasOperationsRole(){
        return roles.contains("content_manager")||roles.contains("admin")||roles.contains("owner");
    }

    private void openExternal(String url){
        ExternalActions.open(this,url);
    }

    private JSONArray array(JSONObject object,String key){
        JSONArray value=object.optJSONArray(key);
        return value==null?new JSONArray():value;
    }

    private String message(Exception error){
        String value=error.getMessage();
        return value==null||value.isBlank()?"Something went wrong.":value;
    }

    private void toast(String value){
        Toast.makeText(this,value,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy(){
        io.shutdownNow();
        super.onDestroy();
    }
}
