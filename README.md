# Statistics Lover Android

Dedicated repository for the Statistics Lover **true native Android client**.

## Current architecture

- The app opens on a native home screen that mirrors the Statistics Lover web home experience.
- Public navigation follows the website structure: Home, Courses, Free Content, Test Series, PYQs, Study Material, About, and Student Login/Dashboard.
- Authenticated navigation uses the same web-style menu pattern instead of the earlier bottom-tab shell.

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


## In-app auto updates

Statistics Lover now follows the Stat Archive update pattern:

1. On launch, the app checks `downloads/version.json` from this repository.
2. If `versionCode` is newer than the installed build, an **Update available** dialog is shown.
3. The APK is downloaded to private app cache.
4. APK size and SHA-256 metadata are verified when present.
5. Android's **Allow from this source** permission is requested when necessary.
6. The downloaded APK is opened through a `FileProvider` and Android's normal package installer.

The release workflow is prepared to publish:

- `downloads/statistics-lover.apk`
- `downloads/version.json`

A persistent signing key is mandatory for Android updates. Configure these GitHub Actions secrets in this repository before using the self-update release channel:

- `KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

After those secrets are present, qualifying `main` builds automatically create the signed release APK and refresh the update metadata. Increment `versionCode` for each version that should be offered as an update.

**Important:** the debug APK uses the `.debug` application ID and debug signing. Install the signed release APK once to enter the persistent self-update channel; a debug APK cannot reliably update itself across fresh GitHub-hosted runners.
