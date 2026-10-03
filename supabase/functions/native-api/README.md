# native-api

Source snapshot of the deployed Supabase Edge Function used by the Statistics Lover native Android client.

At repository split:
- Function status: ACTIVE
- Version: 1
- `verify_jwt=false`
- User-scoped operations use the Supabase anon client plus the user's JWT and therefore remain subject to database RLS.
- No service-role key is used for normal native-user data access.

Keep this source synchronized with the deployed function whenever it changes.
