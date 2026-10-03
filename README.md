# Statistics Lover Android

Dedicated repository for the Statistics Lover **true native Android client**.

## Current architecture

- Launcher flow: `SplashActivity -> NativeMainActivity`
- Native Android UI; the Statistics Lover website is **not** loaded inside a WebView.
- Native screens currently cover sign-in/sign-up/recovery, dashboard, enrolled courses, subject/module/lecture learning hierarchy, notifications, orders, store/order creation, teacher scope, and a role-aware operations shell.
- Google Meet/Google Drive lecture actions open as external provider links.
- Session state is stored locally and the API client supports access-token refresh.
- `FLAG_SECURE` is enabled as a screenshot/screen-recording deterrent.
- Mobile backend adapter: Supabase Edge Function `native-api`, tracked in this repository under `supabase/functions/native-api/`.

## Build

Requires Java 17. GitHub Actions builds the debug APK automatically.

Local build with Gradle 8.10.2:

```bash
gradle --no-daemon assembleDebug
```

APK output:

```
app/build/outputs/apk/debug/app-debug.apk
```

## Source

This repository was split from `ashukla1707-beep/statistics-lover` develop branch after Android checkpoint A3. The original web repository remains untouched during migration.

See `PROJECT_CONTEXT.md` for the Android handoff state and next validation steps.
