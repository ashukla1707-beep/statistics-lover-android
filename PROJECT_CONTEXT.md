# Statistics Lover Android — Project Context

> Canonical handoff file for the dedicated Android repository.

## Repository

- Repository: `ashukla1707-beep/statistics-lover-android`
- Active branch: `main`
- Split from: `ashukla1707-beep/statistics-lover` develop branch.
- Native A3 implementation source commit: `9a4bd97495a2cc074eddcebc7dc1171c5e1ab1ea`.
- Source documentation head at split: `0149b12b4c691e2fb179c0f863f1ff4bf6aa66af`.
- The original Statistics Lover web repository was intentionally left untouched during migration.

## Current direction — A3 true native client

The earlier A1/A2 WebView-based approaches were rejected. The active direction is a true native Android client.

- Launcher flow: `SplashActivity -> NativeMainActivity`.
- The Statistics Lover website is not loaded inside an Android WebView.
- Native screens currently cover sign-in/sign-up/recovery, dashboard, enrolled courses, subject/module/lecture hierarchy, notifications, orders, store/order creation, teacher scope, and a role-aware operations shell.
- Google Meet/Google Drive lecture actions open as external provider links.
- Session state is stored locally and the API client supports token refresh.
- Android `FLAG_SECURE` remains enabled as a screenshot/screen-recording deterrent.

## Mobile API

- Supabase project: Statistics Lover Dev.
- Edge Function slug: `native-api`.
- Source is tracked in `supabase/functions/native-api/`.
- At repository split the deployed function was ACTIVE, version 1, with `verify_jwt=false`.
- It uses the Supabase anon/publishable client plus the authenticated user's JWT for RLS-protected user operations.
- Service-role access is not used for normal native-user data access.

## A3 build record from original repository

- Original Android APK workflow run: `37130958520`.
- Original artifact id: `11277210361`.
- Original ZIP digest: `sha256:9783bf3e20ff69eedf2c0170e9df3fc9d885e622fc70b96ccaf5cac025fbf318`.
- Original extracted APK SHA-256: `c72b271f93ae25b991d64cb4fcf2525ac111adb02cb8f53a22f1e8d7f62e549f`.

## Standalone repository layout

- `app/` — native Android application.
- `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties` — standalone Gradle project.
- `.github/workflows/android-apk.yml` — debug APK CI.
- `supabase/functions/native-api/` — mobile API Edge Function source.
- `PROJECT_CONTEXT.md` — continuity state.

## Immediate next step

Install the freshly built APK on a real Android phone and validate:

1. native login and session restore,
2. enrolled-course learning hierarchy,
3. notification inbox,
4. orders/store/order creation,
5. Google Meet/Drive external routing,
6. role-specific screens,
7. screenshot/screen-recording blocking behavior.

After device validation, continue the native teacher/admin edit workflows.

## Historical note

A1/A2 were WebView/native-shell experiments, including a Stat Archive-style shell. Their obsolete source files are intentionally not carried into this active repository because A3 replaced them with the true native client.
