# Statistics Lover Android — Project Context

> Canonical handoff file for the dedicated Android repository.

## Repository

- Repository: `ashukla1707-beep/statistics-lover-android`
- Active branch: `main`
- Split from: `ashukla1707-beep/statistics-lover` develop branch.
- Native A3 implementation source commit: `9a4bd97495a2cc074eddcebc7dc1171c5e1ab1ea`.
- Source documentation head at split: `0149b12b4c691e2fb179c0f863f1ff4bf6aa66af`.
- The original Statistics Lover web repository was intentionally left untouched during migration.

## PINNED REBUILD BASELINE — Statistics Lover 1.0.15

**This is the canonical Android rebuild starting point unless the user explicitly asks for a later version.**

- Product/version: **Statistics Lover 1.0.15**
- Android `versionCode`: **16**
- Android source commit / workflow HEAD: `bdf050481bbd80a36c974a55ca159174aedf2c34`
- Release commit message: `release: self-contained custom fullscreen fix 1.0.15`
- `app/build.gradle.kts` blob at baseline: `08a0a439954efcc4cab9387cef169aee8d708550`
- `NativeMainActivity.java` blob at baseline: `b273ad430c8d30817bb4bbe067c3c058c07e9ff8`
- Android APK workflow blob at baseline: `56e6ed286f7f672a1ea1f02f15825ea6f3cc931c`
- GitHub Actions release run: **`37147879248` — SUCCESS**
- Release artifact ID: `11283006944`
- Signature verification: **PASSED**
- Published release APK size: **644359 bytes**
- Published APK SHA-256: **`9107d99acf16a7101ac5c29236fac4d6a5a90822ece056a09c8cf597fc9ae818`**
- Auto-update channel at that checkpoint: **1.0.15**
- Public app URL configured in the APK: `https://statistics-lover.vercel.app/`
- Production web commit associated with the working 1.0.15 period: `f309490f75ec004815f19abe2e17c7b7f7d9b471` (`fix: restore Statistics Lover custom fullscreen in Android`).

### What 1.0.15 contains

- Exact responsive Statistics Lover mobile web UI inside the APK WebView.
- Permanent release signing and self-update support.
- Android status/navigation-bar safe-area handling.
- Screenshots and screen recording temporarily allowed.
- Desktop-capable Chrome user agent from app startup so Google Drive recording playback does not require a second reload.
- Statistics Lover custom fullscreen button retained.
- Self-contained native fullscreen fallback injected by `NativeMainActivity`.
- `StatisticsLoverNative` JavaScript bridge for fullscreen enter/exit.
- Native sensor-landscape + immersive bars in fullscreen and portrait restoration on exit.
- Android Back exits custom fullscreen first.
- Recording route keeps the screen awake.
- This version predates the later 1.0.16–1.0.18 player-layout/scaling experiments.

### Rebuild rule

When the user says **“rebuild the working APK,” “rebuild from 1.0.15,” “go back to the good version,”** or otherwise refers to this pinned baseline:

1. Start Android source from commit `bdf050481bbd80a36c974a55ca159174aedf2c34`.
2. Preserve the same package ID: `com.statisticslover.app`.
3. Preserve the same permanent signing key. GitHub Actions expects the existing repository secrets `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, and `KEY_PASSWORD`. **Do not rotate the signing key.**
4. Use the baseline `.github/workflows/android-apk.yml` behavior unless an explicitly requested change requires otherwise.
5. For a new installable release based on this code, increase `versionCode` above every already-published build while keeping the **1.0.15 source behavior** as the functional baseline.
6. Do **not** carry forward the later 1.0.16, 1.0.17, or 1.0.18 player-layout/scaling experiments unless the user explicitly asks for one of those changes.
7. Use the published 1.0.15 APK hash above as the reference artifact identity. A future rebuild with a higher versionCode will naturally have a different SHA-256.
8. If exact 1.0.15 runtime behavior must also be reproduced, remember that the APK loads the live web app; use/restore the compatible production web behavior associated with commit `f309490f75ec004815f19abe2e17c7b7f7d9b471` or keep the APK's self-contained fullscreen fallback authoritative.

**This pinned rebuild baseline takes precedence over later historical checkpoints below when choosing where to restart Android APK development.**

## VERIFIED REBUILD — Statistics Lover 1.0.20

- Rebuilt from the pinned **1.0.15 runtime behavior**; later 1.0.16–1.0.18 player-layout/scaling experiments were removed.
- Product/version: **Statistics Lover 1.0.20**
- Android `versionCode`: **21**
- Runtime restore commit: `a50b564ff70cfee515e55fa5ba11dc6d12b4c174`
- Release source commit: `7fff278316d50bf6ea3970f6d90ea52316acf8fc`
- GitHub Actions run: **37175526613 — SUCCESS**
- Release artifact ID: **11292129608**
- Signed APK verification step: **PASSED**
- Self-update publication step: **PASSED**
- Release APK size: **644359 bytes**
- Release APK SHA-256: **`67827ccf3f11abd963383f23c9d170dc8affc5bdf8bb0723110acb614f81bd3b`**
- Auto-update channel: **1.0.20 / versionCode 21**
- Package ID remains `com.statisticslover.app` and the permanent signing key was preserved.
- Public app URL remains `https://statistics-lover.vercel.app/`.
- This is the current verified installable rebuild when the user asks for the recovered working APK.


## Historical direction — A3 true native client (superseded)

This section records the earlier A3 native-client phase only. It is **not** the current APK rebuild direction. For current rebuild work, use the pinned **Statistics Lover 1.0.15 / versionCode 16** baseline above.

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


## Android checkpoint A11 — recording playback restored from project plan

- User reported the APK showed the web fallback message **Desktop playback required** instead of playing Google Drive recordings.
- Checked the canonical web `PROJECT_CONTEXT.md` and confirmed the previously agreed APK design: the lecture recording route must use a desktop-style Chrome user agent, native WebView fullscreen, and landscape orientation for fullscreen playback.
- Root cause: when the Android shell was changed back to the exact mobile web UI, the earlier recording-route-specific desktop user-agent switching and native fullscreen handling were lost.
- Restored route detection for `/learn/:batchId/lecture/:lectureId`.
- Only the recording route now switches from the normal mobile Statistics Lover WebView user agent to a desktop Chrome user agent; leaving the recording route restores the mobile user agent.
- Route changes through React Router are detected and the recording page reloads once when needed so the web application's mobile-browser gate sees desktop mode.
- Recording pages keep the screen awake.
- Restored Android `WebChromeClient` custom-view fullscreen handling, immersive system bars, sensor landscape during fullscreen, and portrait restoration on exit.
- Android Back exits fullscreen before navigating back.
- The rest of the APK remains the exact responsive mobile web layout and keeps the safe-area fix.
- Version advanced to versionCode 10 / versionName 1.0.9.
- Signed workflow run `37143466485` succeeded end-to-end.
- Release artifact ID: `11281097186`.
- Published APK size: 643151 bytes.
- Published APK SHA-256: `ed939712f26d9a6000f989a2f5be42ba570f171a715c6b57749407761d6cd3a8`.
- Self-update metadata now publishes version 1.0.9, so an installed signed 1.0.8 should be offered this update on launch.


## Android checkpoint A12 — definitive recording gate bypass

- User reported that even after 1.0.9 the lecture page still showed **Desktop playback required**.
- Root cause identified: the web lecture page checks `navigator.userAgentData.mobile` before falling back to `navigator.userAgent`. Android WebView may continue reporting `mobile: true` even after the native shell switches to a desktop Chrome user agent, so the web gate still activated.
- Web fix committed to both Statistics Lover `develop` and production `main`: the lecture page now checks for the explicit `StatisticsLoverAndroid/` app marker first and bypasses the mobile-browser gate inside the APK.
- Android recording desktop user agent now preserves the same `StatisticsLoverAndroid/<version>` marker.
- Normal mobile website visitors still receive the desktop-site gate; only the signed Android app bypasses it.
- Production Vercel deployment for web commit `b45792a8d79a98d6aabbef5d51efc0454e4372fb` is READY and owns `statistics-lover.vercel.app`.
- Web Quality run `37144132303` passed.
- Android version advanced to versionCode 11 / versionName 1.0.10.
- Signed Android workflow run `37144147948` succeeded end-to-end.
- Release artifact ID: `11281048193`.
- Published APK size: 643167 bytes.
- Published APK SHA-256: `90ae77df5835673ffd2d1890eb4e3b14d491a178d8e7bf9005db63f10c3d5dc7`.
- Self-update metadata now advertises 1.0.10.


### A12 follow-up — invalidate stale WebView lecture bundle
- The Statistics Lover service worker cache was bumped from `statistics-lover-static-v1` to `statistics-lover-static-v2` on both web `develop` and production `main`.
- This forces old cached JavaScript containing the mobile recording gate to be discarded after the new service worker activates.
- Production web deployment for commit `ee8157ddd3eb5d1d612ecc8955d18375e6f8a5e4` is READY on `statistics-lover.vercel.app`.
- Production Quality run `37144473341` passed.


## Android checkpoint A13 — temporary screen capture enabled

- User requested screenshots and screen recording to be enabled temporarily for testing.
- Removed Android `FLAG_SECURE` from `NativeMainActivity`.
- Screenshots and Android screen recording are now allowed throughout the app, including lecture playback.
- This is a temporary product/testing decision and reduces the previous capture deterrent; re-enable `FLAG_SECURE` later if content-protection policy requires it.
- No other navigation, playback, safe-area, signing, or auto-update behavior was changed.
- Version advanced to versionCode 12 / versionName 1.0.11.
- Signed Android workflow run `37144779193` succeeded end-to-end.
- Release artifact ID: `11281842651`.
- Published APK size: 643155 bytes.
- Published APK SHA-256: `93bb981aac1e13859d9e85494418752ee2866a0febcac742dba76e2f6a38fb4b`.
- Self-update metadata now advertises version 1.0.11.


## Android checkpoint A14 — fullscreen rotation reload fixed

- User supplied a screen recording showing lecture playback working inline, but tapping fullscreen caused the screen to rotate, briefly show a white/loading state, then return to portrait/inline playback.
- Root cause: entering landscape fullscreen triggered an Android configuration/orientation change that recreated `NativeMainActivity` and therefore rebuilt the WebView. The React lecture page reloaded and showed `Loading recording…` instead of preserving the live Google Drive player.
- Android fix: `NativeMainActivity` now handles orientation/screen-size related configuration changes itself through manifest `configChanges`, preventing Activity/WebView recreation during fullscreen rotation.
- Web fix: when `StatisticsLoverAndroid/` is detected, the lecture page no longer calls the browser `screen.orientation.lock()/unlock()` APIs. Native Android remains the single owner of fullscreen orientation, avoiding competing orientation locks.
- Existing native `WebChromeClient` custom-view fullscreen, immersive bars, sensor-landscape entry, portrait restoration, back-to-exit-fullscreen, screen-awake behavior, mobile web layout, screen capture support, and recording app-marker logic remain in place.
- Web production deployment for commit `091942ba1c6bd3566e98321e957a7eadee69268f` is READY on `statistics-lover.vercel.app`.
- Web Quality run `37145298542` passed.
- Android version advanced to versionCode 13 / versionName 1.0.12.
- Signed Android workflow run `37145306840` succeeded end-to-end.
- Release artifact ID: `11280954558`.
- Published APK size: 643183 bytes.
- Published APK SHA-256: `3c8583567f7957db4ac2a48cb6c0bc7e52597200f3ead4ba938f19632b09279f`.
- Self-update metadata now advertises version 1.0.12.


## Android checkpoint A15 — remove intermediate recording layout/reload

- User supplied a screen recording showing that tapping **Watch recording** first displayed the lecture route's `Loading recording…` state, then the page layout changed, and only afterwards did the Google Drive player appear.
- Root cause: the Android WebView originally started with the normal mobile user-agent. Entering the lecture route then switched the WebView to a desktop Chrome user-agent required by Google Drive and reloaded the route. That user-agent transition created an unnecessary second page load and visible intermediate layout.
- Verified the web application has no other mobile-user-agent-dependent behavior outside `LecturePlayerPage.tsx`; the responsive mobile layout is controlled by viewport/CSS.
- Android fix: the WebView now uses the desktop Chrome user-agent plus the explicit `StatisticsLoverAndroid/<version>` marker from app startup.
- Because the real phone viewport width is unchanged, the Statistics Lover UI remains in its responsive mobile layout while Google Drive receives the desktop-capable user-agent immediately.
- Recording route detection now only controls screen-awake behavior; it no longer changes user-agent or reloads the WebView.
- Result: tapping **Watch recording** should navigate once to the lecture page and load the player directly, without the previous mobile-UA → desktop-UA reload/layout transition.
- Existing fullscreen rotation fix, native fullscreen/landscape behavior, safe-area handling, signed updater, and temporary screenshot/screen-recording support remain unchanged.
- Version advanced to versionCode 14 / versionName 1.0.13.
- Signed Android workflow run `37145903963` succeeded end-to-end.
- Release artifact ID: `11281354398`.
- Published APK size: 642963 bytes.
- Published APK SHA-256: `3f9153ec6812c47b8c97d8a33f5b8a4198c85a560d895cc40b2806f3a3743489`.
- Self-update metadata now advertises version 1.0.13.


### A15 follow-up — Drive native fullscreen controls in APK

- User reported that fullscreen video opened, but some Google Drive playback controls were missing/blank.
- Root cause: the APK was using the Statistics Lover outer-stage custom fullscreen button. That made the outer stage fullscreen while Google Drive still considered itself an embedded desktop player, so parts of Drive's own control bar could render incorrectly.
- Web player now suppresses the custom Statistics Lover fullscreen button when `StatisticsLoverAndroid/` is detected.
- Inside the APK, fullscreen is now entered through Google Drive's own fullscreen control. Android `WebChromeClient` continues to handle the resulting fullscreen custom view, immersive system bars and landscape orientation.
- The Statistics Lover logo overlay remains for the normal embedded player; Drive owns its own fullscreen control surface.
- Service-worker cache advanced to `statistics-lover-static-v3` so the corrected player bundle replaces stale cached code.
- Production deployment for web commit `ec07f4bce734a71a602595236f99f43d4ec70bb4` is READY on `statistics-lover.vercel.app`.
- This is a web-layer fix and does not require a new APK version beyond 1.0.13; fully close/reopen the APK so the refreshed web bundle activates.


## Android checkpoint A16 — stable custom fullscreen bridge + self-contained fallback

- User supplied a screen recording showing the Statistics Lover custom fullscreen entering landscape, flashing/rotating awkwardly, then collapsing back to portrait.
- Root cause: the custom button still relied on the browser/WebView Fullscreen API (`requestFullscreen()`). Android then rotated the Activity; WebView dropped the fullscreen custom-view lifecycle during the orientation transition, producing the sideways/black transition and exit back to portrait.
- Architecture changed: Statistics Lover custom fullscreen no longer depends on the browser Fullscreen API inside the APK.
- Added a minimal Android JavaScript bridge named `StatisticsLoverNative` with recording-route-guarded `enterFullscreen()` and `exitFullscreen()` methods.
- Web fullscreen mode expands the lecture stage into a fixed full-viewport layer; native Android alone controls sensor-landscape orientation, immersive system bars, screen-awake behavior, and portrait restoration.
- Android Back exits the custom fullscreen mode first.
- Added an Android-side injected fallback for the current production web bundle. It intercepts the Statistics Lover fullscreen button, applies real-viewport fullscreen CSS, and calls the native bridge. This makes the fix work even if the newest web deployment is delayed.
- Vercel reported `Deployment rate limited — retry in 24 hours.` for the newest web fullscreen commits, so the self-contained APK fallback intentionally removes immediate dependence on Vercel production.
- The newer web implementation remains committed to both `develop` and `main`, with service-worker cache advanced to `statistics-lover-static-v5`; once Vercel can deploy again, the website and APK-native implementation converge on the same behavior.
- Version advanced to versionCode 16 / versionName 1.0.15.
- Signed Android workflow run `37147879248` succeeded end-to-end.
- Release artifact ID: `11283006944`.
- Published APK size: 644359 bytes.
- Published APK SHA-256: `9107d99acf16a7101ac5c29236fac4d6a5a90822ece056a09c8cf597fc9ae818`.
- Self-update metadata now advertises version 1.0.15.


## Android checkpoint A16 — intermittent player layout race removed

- User reported that lecture playback was generally working but sometimes the inline Google Drive control layout rendered incorrectly.
- Root cause identified in the web player: the Android APK was still passing through the legacy touch-device 1024x576 scaling path. Depending on when the WebView/iframe finished measuring, Drive controls could be positioned using a stale `--drive-player-scale` value, producing an intermittent distorted/partial control layout.
- Web fix committed to both Statistics Lover `develop` and `main`: Android is excluded from `syncTouchPlayerScale`, and Android-specific CSS uses the real player element size for both inline and custom fullscreen modes.
- APK fix is self-contained as well: `NativeMainActivity` injects an Android-only CSS override into the WebView so the real-size player layout is enforced immediately even if production Vercel is still serving an older bundle.
- Normal mobile-browser behavior is unchanged.
- Custom Statistics Lover fullscreen, native Android fullscreen bridge, safe-area handling, screen-capture testing mode, recording playback, and signed updater remain in place.
- Version advanced to versionCode 17 / versionName 1.0.16.
- Signed Android workflow run `37148487788` succeeded end-to-end.
- Release artifact ID: `11282559362`.
- Published APK size: 644623 bytes.
- Published APK SHA-256: `caf3be46c555a7f3ef2e8fc4240d415d4267a71d4224abe5109bbb0d7916065b`.
- Self-update metadata now advertises version 1.0.16.


## Android checkpoint A16 — 1.0.16 player-layout change reverted

- User requested the last 1.0.16 player-layout change be reverted.
- Restored `NativeMainActivity.java` exactly to the pre-1.0.16 / 1.0.15 behavior.
- Reverted the corresponding web player changes that removed the legacy scaling path on both `main` and `develop`.
- Service-worker cache advanced to `statistics-lover-static-v6` so the rollback replaces any cached 1.0.16 player assets when the web deployment updates.
- Rollback is published as versionCode 18 / versionName 1.0.17 so it can install over 1.0.16.
- Signed Android workflow run `37148951468` succeeded end-to-end.
- Release artifact ID: `11282174021`.
- Published APK size: 644363 bytes.
- Published APK SHA-256: `0b433ebe740ac7273c4ca39be70768a5b1368420c2b5ff624a506fb373bd9f96`.
- Self-update metadata now advertises version 1.0.17.


## Android checkpoint A17 — compact custom fullscreen controls

- User reported that after the 1.0.17 rollback, tapping the Statistics Lover custom fullscreen button still produced Google Drive's large touch-oriented control panel, unlike the compact desktop-style appearance they expected from the earlier working state.
- Verified that the 1.0.17 Android fullscreen source is byte-for-byte identical to the 1.0.15 Android fullscreen source, and the public production web alias is still serving the same web commit used during the 1.0.15 period. The remaining difference therefore comes from Google Drive's responsive iframe control layout/state when the iframe is expanded to the full landscape viewport.
- Custom Statistics Lover fullscreen is retained.
- Android's injected fullscreen fallback now renders Google Drive on a fixed 1024x576 desktop canvas and scales that canvas to fit the real landscape viewport. This keeps Drive in its compact desktop control layout while preserving the Statistics Lover custom fullscreen/exit button.
- Native landscape/immersive mode remains controlled by the StatisticsLoverNative bridge.
- Fullscreen scale is recalculated on entry, resize, and orientation changes.
- Native safe-area padding is forced to zero while custom fullscreen is active and normal system-bar safe-area padding is restored on exit.
- Version advanced to versionCode 19 / versionName 1.0.18.
- Signed Android workflow run `37149458941` succeeded end-to-end.
- Release artifact ID: `11283396630`.
- Published APK size: 644767 bytes.
- Published APK SHA-256: `24279baccbfbac8d5300ad5a18cf21bb38f58fe671860d70b14fe06d0b036bf1`.
- Self-update metadata now advertises version 1.0.18.


## VERIFIED RELEASE — Statistics Lover 1.0.21 fullscreen stabilization

- User supplied a real-device screen recording showing the custom fullscreen transition rotating into a sideways/narrow player, briefly tearing during orientation change, and settling with portrait-style geometry/system-bar space instead of a stable landscape fullscreen.
- Root cause: the Statistics Lover custom fullscreen bridge and Google Drive's own iframe/WebChromeClient custom-view fullscreen could both become fullscreen/orientation owners. The native shell also retained normal safe-area padding while immersive bars were being hidden, and the sensor-landscape mode could flip during the transition.
- Fix source commit: `c84fda9f4a44ad0d891ee6ec129a02f22e57d433`.
- Product/version: **Statistics Lover 1.0.21**
- Android `versionCode`: **22**
- GitHub Actions run: **37177622093 — SUCCESS**
- Signed release artifact ID: **11293579153**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **644607 bytes**
- Published APK SHA-256: **`0ebef70a62cf5066494caabee8c057fb4e7a65472f99fcae213825919e700495`**
- Auto-update channel is now **1.0.21 / versionCode 22**.
- Fullscreen behavior changes:
  - Statistics Lover custom fullscreen is the only authoritative fullscreen path.
  - Google Drive nested `WebChromeClient` custom-view fullscreen requests are rejected to prevent mixed fullscreen states.
  - The injected fallback no longer capture-blocks the React fullscreen click; it acts only as a backup if the live page does not apply its fullscreen class.
  - In app fullscreen, the custom control occupies the Drive fullscreen hit area so only one fullscreen control is practically available.
  - Fullscreen uses fixed landscape instead of sensor-landscape to avoid orientation flips during transition.
  - Native safe-area padding is removed while fullscreen is active and restored on exit.
  - WebView layout/resize/orientation events are re-synchronized several times through the rotation transition so the Drive iframe receives the final landscape viewport.
  - Immersive system bars are re-applied when window focus returns.
- Existing package ID and permanent signing certificate were preserved; installed 1.0.20 release builds can receive 1.0.21 through the existing in-app updater.
