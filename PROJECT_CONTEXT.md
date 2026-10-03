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


## Interface checkpoint A4 — web-style native home

- User rejected the initial A3 dashboard-first interface.
- APK launch now opens a native public home screen modeled on the Statistics Lover web home rather than opening Login/Dashboard first.
- The home screen mirrors the web hero, learning-area cards, course/free-content/test/PYQ messaging, and brand/tagline.
- Mobile navigation now follows the web structure through a hamburger menu: Home, Courses, Free Content, Test Series, PYQs, Study Material, About, and Student Login/Dashboard.
- Authenticated screens use a matching menu approach for Dashboard, My Courses, Notifications, My Orders, Courses, teacher/admin areas, and Logout; the earlier bottom navigation was removed.
- The implementation remains fully native Android; no WebView was reintroduced.
- Public Courses can be browsed before login; creating an order requires authentication.
- Login copy and course-store copy were aligned with the web product language.
- Immediate acceptance test: install the A4 APK and compare launch/home/navigation feel against the Statistics Lover mobile website.


## Android checkpoint A5 — Stat Archive-style auto-update foundation

- Ported the Stat Archive self-update pattern into the native Statistics Lover client.
- App launch now checks repository-hosted `downloads/version.json` for a newer `versionCode`.
- Update dialog supports Update/Later; update download runs off the UI thread and never blocks normal app startup if the check fails.
- Downloaded APK is stored under private cache `updates/`, exposed only through AndroidX `FileProvider`, and installed through Android's package installer.
- Android 8+ unknown-source permission flow is handled with `ACTION_MANAGE_UNKNOWN_APP_SOURCES`; returning to the app resumes installation.
- Added `REQUEST_INSTALL_PACKAGES` and the update FileProvider path.
- Added APK size and SHA-256 verification in addition to the Stat Archive baseline behavior.
- App version advanced to versionCode 4 / versionName 1.0.3.
- GitHub workflow now always builds/verifies/uploads the debug APK and is also prepared to build/publish a stable signed release update channel.
- When signing is configured, the workflow publishes `downloads/statistics-lover.apk` and refreshes `downloads/version.json` automatically from the release build.
- Required repository Actions secrets for stable release signing: `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.
- Current new repository does not yet have those release signing secrets; run `37133882202` passed the debug build and correctly skipped release publication.
- This is intentional: Android package updates require the same persistent signing certificate. Fresh GitHub debug keys must not be used as an update channel.
- Once signing secrets are configured, install the signed release APK once; subsequent higher-version signed releases can use the in-app update flow.


## A6 signing key generated

- A permanent Statistics Lover Android release signing keystore has been generated outside GitHub and is **not committed to the repository**.
- Release key alias: `statisticslover`.
- Certificate SHA-256 fingerprint: `C4:7F:CF:18:41:93:14:B5:2C:76:A9:E3:A0:E5:F8:F4:E1:D0:80:3B:63:15:B8:B7:AB:D7:A3:BE:1D:B5:12:1F`.
- Keystore file SHA-256: `7a9aec47a4666571dbc8f346424f96d0cf6331cbd97465afe402750223afc5b8`.
- Required next action: add the generated values to GitHub Actions secrets `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, and `KEY_PASSWORD`.
- Never commit the keystore, passwords, or Base64 keystore value to Git. Losing or replacing this key would break Android's same-signing-certificate update chain for existing release installs.


## A6 complete — first permanently signed release

- GitHub Actions secrets for the permanent release key are configured and validated by a successful signed build.
- First signed release: versionCode 5 / versionName 1.0.4.
- Workflow run: `37140954180` — SUCCESS.
- Release signing configuration detected successfully.
- Keystore restoration succeeded.
- Signed release APK build succeeded.
- APK signature verification succeeded.
- Release artifact upload succeeded.
- Self-update publication succeeded.
- Published APK: `downloads/statistics-lover.apk`.
- Published metadata: `downloads/version.json`.
- Published APK size: 658807 bytes.
- Published APK SHA-256: `f688b24949c622648b0c4ab79eace13ad72250d56912dd62ca17ac1b5f37ff8b`.
- Release artifact ID: `11280511616`.
- This 1.0.4 signed release is the baseline install for the permanent in-app update chain. Future releases must use the same signing secrets and a higher versionCode.


## Android checkpoint A7 — exact mobile web layout inside APK

- User rejected the native approximation of the web layout and requested the APK to look exactly like the website in mobile mode.
- Android shell now renders the actual Statistics Lover responsive web application inside the app's own WebView, with no browser address bar or external browser chrome.
- Release/home URL: `https://hstatistics.workers.dev/`.
- Normal Statistics Lover navigation remains inside the APK; external hosts such as Meet/Drive/payment/deep links open through Android handlers.
- JavaScript, DOM storage, cookies, responsive viewport behavior, file chooser support, downloads/external-link routing, and browser-style back navigation are enabled.
- `FLAG_SECURE` and the signed in-app updater remain active.
- Version advanced to versionCode 6 / versionName 1.0.5.
- Signed workflow run `37141381121` succeeded.
- Release artifact ID: `11280836419`.
- Published self-update metadata points to version 1.0.5.
- Published APK size: 640287 bytes.
- Published APK SHA-256: `6da11ed1a1af340931ca11ed57af59e16e3bebf7e592b21250479288db2b3f2f`.
- CI publication race protection was added so stale concurrent builds cannot overwrite the newest update channel.
- Acceptance target: the APK should visually match the Statistics Lover mobile website because it now uses the exact same responsive web UI rather than duplicating the design natively.


## Android checkpoint A8 — live host DNS fix

- User reported that 1.0.5 opened `https://hstatistics.workers.dev/` and failed with `net::ERR_NAME_NOT_RESOLVED`.
- The workers.dev hostname is not a reliable runtime host and was removed from the Android launch configuration.
- Verified the active Statistics Lover Vercel develop alias returns HTTP 200 with the current mobile web app: `https://statistics-lover-git-develop-statistics-lover.vercel.app/`.
- Android WebView launch URL now uses that stable Vercel alias.
- Version advanced to versionCode 7 / versionName 1.0.6.
- Signed workflow run `37141748337` succeeded end-to-end.
- Release artifact ID: `11280837022`.
- Self-update metadata now publishes version 1.0.6.
- Published APK size: 640295 bytes.
- Published APK SHA-256: `630327a6a2f76d5b7a4f6397f66ee3001b6d87cca5f5afb29fbf5298ca643afe`.
- The signed 1.0.5 build should detect 1.0.6 through the in-app updater; 1.0.6 can also be installed manually over the signed 1.0.5 build.


## Android checkpoint A9 — public production web host

- User reported 1.0.6 was redirected from the protected Vercel preview deployment to `vercel.com/login`, which then opened in the external browser.
- Root cause: the APK was using a Vercel preview alias protected by Vercel Authentication.
- The Statistics Lover web production alias was force-refreshed from the verified `main` branch.
- Verified production deployment `dpl_BwUW93vPC9DVkRQ1ApTAPxKRruu3` is READY and owns `statistics-lover.vercel.app`.
- Android WebView launch URL now uses the public production origin `https://statistics-lover.vercel.app/`.
- Version advanced to versionCode 8 / versionName 1.0.7.
- Signed Android workflow run `37142184930` succeeded end-to-end.
- Release artifact ID: `11280980773`.
- Published self-update metadata now points to 1.0.7.
- Published APK size: 640291 bytes.
- Published APK SHA-256: `be2d3e6e665e8c5ead63e1fa7d173fca59d75ad3764f6291de040d8e24cf8adb`.
- Expected behavior: Statistics Lover navigation stays inside the APK WebView; normal app startup no longer redirects to Vercel login or launches the browser.


## Android checkpoint A10 — system bar safe-area fix

- User reported that the top of the APK overlapped the Android notification/status bar.
- Root cause: Android 15 / targetSdk 35 edge-to-edge behavior allowed the WebView root to draw beneath system bars.
- Added Android system-window inset handling at the shell level using status bar, navigation bar, and display-cutout insets.
- The web layout itself remains unchanged; only the native container now pads content into the safe area.
- Version advanced to versionCode 9 / versionName 1.0.8.
- Signed workflow run `37142528002` succeeded end-to-end.
- Release artifact ID: `11280304868`.
- Published APK size: 641859 bytes.
- Published APK SHA-256: `925c3b15e133ca757d61e6aca4efc50b373fdea4b3f7234fb1f810382e5790a5`.
