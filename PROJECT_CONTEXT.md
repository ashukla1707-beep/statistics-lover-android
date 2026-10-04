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


## VERIFIED RELEASE — Statistics Lover 1.0.22 compact fullscreen geometry

- User clarified the target with two screenshots: the undesired fullscreen was the stretched desktop Drive interface; the desired fullscreen is the compact 16:9 Drive layout with smaller controls matching the stable 1.0.18-era presentation.
- Root cause of the undesired first screenshot: 1.0.21 deliberately stretched the Drive iframe and overlay to the full Android fullscreen viewport at 100% width/height. That made Google Drive render its large desktop control layout.
- Fix source commit: `100b1e582da341d6cd153b9d183296a78ae3d28f`.
- Product/version: **Statistics Lover 1.0.22**
- Android `versionCode`: **23**
- GitHub Actions run: **37178429643 — SUCCESS**
- Signed release artifact ID: **11294076787**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **644751 bytes**
- Published APK SHA-256: **`65b0b90f9db13dd55ff162079db6f1ae390e7c0286db99a074f07907d61586d8`**
- Auto-update channel is now **1.0.22 / versionCode 23**.
- Fullscreen layout behavior:
  - Keeps the 1.0.21 native rotation, safe-area, WebView reflow, signing and updater fixes.
  - Restores the proven compact player geometry from the 1.0.18 fullscreen implementation.
  - Drive iframe uses a fixed 1024x576 canvas and scales uniformly to fit the available fullscreen viewport instead of stretching.
  - The Statistics Lover overlay is scaled with the same canvas so the custom fullscreen button and logo remain aligned with the Drive player.
  - Scale is recalculated on resize/orientation events and again during the transition.
  - The custom button remains the user-facing fullscreen control.
- Existing package ID and permanent signing certificate remain unchanged; signed 1.0.20/1.0.21 installs can receive 1.0.22 through the existing in-app updater.


## VERIFIED RELEASE — Statistics Lover 1.0.23 landscape fullscreen + current homepage

- User identified two remaining issues after 1.0.22:
  1. Statistics Lover custom fullscreen must enter **landscape mode** immediately.
  2. The APK was still loading the older production homepage instead of the working homepage stabilized earlier on the Vercel `develop` alias.
- Root causes verified:
  - `NativeMainActivity` was still declared with `android:screenOrientation="portrait"` in the manifest, which could fight the runtime landscape request.
  - Both debug and release `APP_URL` still pointed to `https://statistics-lover.vercel.app/`, whose bundle is older than the current `develop` deployment.
- Fix source commit: `1f1d510059de11116913ecdb3ced82d8438c11ad`.
- Product/version: **Statistics Lover 1.0.23**
- Android `versionCode`: **24**
- GitHub Actions run: **37179092335 — SUCCESS**
- Signed release artifact ID: **11294711461**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **644851 bytes**
- Published APK SHA-256: **`01c2098f2e4ae24acf21827aabaf0a6868a7f446ade30e7b1345fbc88882bb92`**
- Auto-update channel is now **1.0.23 / versionCode 24**.
- Fullscreen changes:
  - removed the manifest-level portrait lock from `NativeMainActivity`;
  - normal app mode explicitly requests portrait in `onCreate`;
  - Statistics Lover fullscreen explicitly requests `SCREEN_ORIENTATION_LANDSCAPE`;
  - landscape is reasserted at 80 ms and 240 ms through the transition to handle OEM timing;
  - 1.0.22 compact 1024x576 fullscreen geometry remains unchanged.
- Homepage/web source changes:
  - debug and release `APP_URL` now use `https://statistics-lover-git-develop-statistics-lover.vercel.app/`;
  - this is the stable Vercel `develop` alias containing the homepage/header/footer/responsive fixes completed earlier in the project;
  - production `statistics-lover.vercel.app` is no longer used by this APK until the user explicitly promotes the current develop web build to production.
- Existing package ID, permanent signing certificate and auto-update lineage remain unchanged, so signed 1.0.20–1.0.22 installs can update to 1.0.23 in-app.


## VERIFIED RELEASE — Statistics Lover 1.0.24 session-origin recovery

- User reported that 1.0.23 behaved like a fresh web visit and asked for sign-in again.
- Root cause: 1.0.23 changed the APK origin from `statistics-lover.vercel.app` to the `develop` Vercel hostname. Supabase/browser session storage is origin-scoped, so the existing app session could not be read on the new host.
- Corrective strategy:
  - restore the original stable web origin so existing WebView/Supabase session data remains available;
  - launch directly at `https://statistics-lover.vercel.app/dashboard` instead of the public homepage;
  - update the production Vercel alias itself to the current working develop deployment so the corrected homepage/UI lives on the same stable origin.
- APK source commit: `253bc5650fe325a4d4fd83e70b854e8b8b18780e`.
- Product/version: **Statistics Lover 1.0.24**
- Android `versionCode`: **25**
- GitHub Actions run: **37179541593 — SUCCESS**
- Signed release artifact ID: **11294701290**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **644863 bytes**
- Published APK SHA-256: **`d10e99dff759d5a60ab8dd47aa17f854047934a074edb8265da4c674cc9644d6`**
- Auto-update channel is now **1.0.24 / versionCode 25**.
- Vercel production alias `statistics-lover.vercel.app` was reassigned to deployment `dpl_BRMCC4xjefkc5DAowgaRgwyxBzUP`, which corresponds to the current working develop web bundle. Verification showed production and develop serving the same JS bundle `index-DC55xXSS.js`.
- This keeps the old stable origin for stored login/session continuity while delivering the working homepage/header/footer/responsive fixes on that same origin.
- Fullscreen behavior remains from 1.0.23: compact 1024x576 geometry, Statistics Lover custom fullscreen, and explicit landscape transition.


## VERIFIED RELEASE — Statistics Lover 1.0.26 website UI inside APK

- User clarified that the APK must render the **same responsive website UI** and must not substitute a separate Android-specific header/bottom-navigation interface.
- Root cause of the unwanted UI: the APK sent `StatisticsLoverAndroid/<version>` in its user-agent on every page. The web app used that marker to render `AndroidAppHeader` and `AndroidBottomNav` instead of the normal website `Header` and `Footer`.
- Correct architecture:
  - ordinary APK pages use the WebView's normal mobile website user-agent and therefore render the same responsive web UI as a browser;
  - only the recording route switches to the desktop Chrome + `StatisticsLoverAndroid/<version>` marker needed by the Google Drive player and native fullscreen bridge;
  - leaving the recording route switches the user-agent back to normal website mode;
  - native Android remains responsible only for the thin shell: session-preserving WebView, updates, file picker, external-link routing, system insets and recording fullscreen/orientation.
- Source commits:
  - `ce99c08224bbb3dc45799abb44995190d02428de` — route-scoped Android integration mode + version 1.0.26.
  - `db01d079bbbf4c7c0c6f3e4df3e0a3de08cff185` — ordinary pages use the real responsive WebView/mobile user-agent.
- Product/version: **Statistics Lover 1.0.26**
- Android `versionCode`: **27**
- GitHub Actions run: **37180060760 — SUCCESS**
- Signed release artifact ID: **11294094436**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **645119 bytes**
- Published APK SHA-256: **`b9fa02ead68b6db5f044ee330a99d9df64f6fef25cabc961c74bf756707bde42`**
- Auto-update channel is now **1.0.26 / versionCode 27**.
- Stable app origin remains `https://statistics-lover.vercel.app/`, preserving existing WebView/Supabase session storage.
- Fullscreen behavior remains the compact landscape implementation established in 1.0.22–1.0.24.
- The prior 1.0.25 build is superseded because it still relied on the web bundle being redeployed before the Android-specific UI switch would disappear.


## VERIFIED RELEASE — Statistics Lover 1.0.27 single-transition recording startup

- User supplied the same class of real-device recording startup problem previously fixed at Android checkpoint A15: tapping **Watch recording** showed multiple intermediate loading/layout states before Google Drive playback appeared.
- Historical fix recovered from commit `293b1a2e88f4667173f04d4fa8e48d5e4e05578b` / release 1.0.13:
  - the WebView must use the Drive-capable desktop user-agent from app startup;
  - entering a lecture route must **not** change user-agent and must **not** reload the route.
- 1.0.26 had reintroduced the old A15 failure mode by switching user-agent when entering/leaving the recording route.
- 1.0.27 restores the A15 single-navigation model while preserving the user's newer requirement that normal APK screens look like the website:
  - actual WebView/network UA is desktop Chrome + `StatisticsLoverAndroid/<version>` from startup, so Google Drive is ready immediately;
  - a document-start JavaScript mask dynamically hides the Android marker from normal website routes;
  - the marker is exposed to page JavaScript only when the current pathname is a lecture route;
  - therefore normal pages render the website UI, while the lecture page still recognizes native Android integration/fullscreen;
  - recording route detection now controls screen-awake/fullscreen state only and never changes UA or calls `loadUrl`.
- This removes the extra mobile-UA → desktop-UA route reload responsible for the repeated loading/layout transition. The remaining Google Drive iframe loading indicator is provider loading, not a second Statistics Lover page reload.
- Source commit: `84384549591f3e98c8c4342a98e5ae1900d03cc6`.
- Product/version: **Statistics Lover 1.0.27**
- Android `versionCode`: **28**
- GitHub Actions run: **37180641112 — SUCCESS**
- Signed release artifact ID: **11294579346**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **655138 bytes**
- Published APK SHA-256: **`e98b55697aed956ab6e35af38008e939eb19c6583e21ef5dfd141b7042ade991`**
- Auto-update channel is now **1.0.27 / versionCode 28**.
- Existing compact landscape fullscreen, stable production origin/session continuity, file picker, external links and permanent signing certificate are preserved.


## VERIFIED RELEASE — Statistics Lover 1.0.29 Drive seek-control stabilization

- User reported that tapping/dragging the Google Drive video time/progress line could leave the seek UI visually stuck even though the video continued to play.
- Real-device evidence showed playback continuing underneath a pinned Drive seek/preview state.
- Two causes were addressed together:
  - desktop Google Drive controls on a touch device can retain a synthetic mouse/scrub hover state after `ACTION_UP`;
  - transient fullscreen viewport resize events could re-scale the transformed 1024x576 player canvas during seek interaction, disrupting Drive's pointer state.
- Source commits:
  - `d07c1e475485827a63cba505bcf8eff287f1ee67` — after fullscreen touch release, explicitly clears Drive's stuck scrub state with a touch cancel plus mouse hover exit.
  - `75df561f9414c4bff6d0812319584d21627bf199` — freezes compact fullscreen scale during normal resize events and re-syncs only on orientation changes.
- Product/version: **Statistics Lover 1.0.29**
- Android `versionCode`: **30**
- GitHub Actions run: **37181709525 — SUCCESS**
- Signed release artifact ID: **11295292272**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **655582 bytes**
- Published APK SHA-256: **`6fd0353834cd513d2af8a61784add36e833e78f3f41c1b46a687a31212410751`**
- Auto-update channel is now **1.0.29 / versionCode 30**.
- Existing website-style UI, single-transition recording startup, stable production origin/session continuity, compact landscape fullscreen, permanent signing certificate and updater lineage are preserved.


## Web-delivered startup behavior for installed APKs

- Current signed APK baseline remains **Statistics Lover 1.0.29 / versionCode 30**; no Android rebuild was needed for this change.
- 1.0.29 already launches the stable origin root: `https://statistics-lover.vercel.app/`.
- Web commit `d12f60e5b2258021d72179aa2ffad47f8c2a86c5` now makes the APK root auth-aware:
  - no persisted session -> Home;
  - authenticated session -> Dashboard;
  - suspended account -> Account suspended.
- The decision waits for Supabase session hydration, so signed-in users do not flash the public Home page first.
- APK detection uses the always-present `StatisticsLoverNative` JavaScript bridge, not the Android UA marker.
- Browser users at `/` still receive the normal Home page.
- Production Vercel origin was updated to deployment `dpl_9gqkdAYqoHbvaeoY3LG3gKNVK4Sq`; production and preview both serve bundle `index-DUc0RxZf.js`.


## VERIFIED RELEASE — Statistics Lover 1.0.30 smooth auth-aware launch + circular splash

- User requested three connected launch/navigation corrections:
  - cold app launch should transition smoothly while the website/session is loading;
  - signed-out launch -> Home, signed-in launch -> Dashboard;
  - tapping **Home** from the website menu must always show Home even when signed in.
- Web startup routing was separated from normal Home navigation:
  - APK cold start now uses the dedicated route `/app-start`;
  - `/app-start` waits for Supabase session hydration, then routes authenticated -> `/dashboard`, suspended -> `/account-suspended`, anonymous -> `/`;
  - normal `/` is always the real Home page, so the menu's Home link no longer redirects authenticated users to Dashboard.
- Web commit: `f874aad193f934a098c94ffb688bb66692504d72`.
- Web Quality run: `37183208568 — SUCCESS`.
- Vercel deployment: `dpl_8j8Fbyyfna9woyqwe2tbTuSk7PCH` — READY and assigned to `statistics-lover.vercel.app`.
- Android launch was changed from two Activities to a single launcher Activity:
  - the obsolete 350 ms `SplashActivity` handoff was removed;
  - `NativeMainActivity` now owns the Android SplashScreen API directly;
  - the WebView starts loading immediately behind a native splash overlay instead of waiting for a second Activity;
  - the overlay remains visible until the web app calls `StatisticsLoverNative.appReady()` after auth-aware routing and page rendering;
  - a 10-second fallback prevents a stale/old web bundle from leaving the splash permanently visible.
- Splash branding:
  - uses the existing Statistics Lover logo asset from the web repository; no generated replacement artwork is used;
  - the native overlay clips the logo to a true circular outline;
  - the logo is centered on the same light splash background with a small loading indicator;
  - WebView content fades in (180 ms) while the circular splash fades out (220 ms), eliminating the blank/rough transition.
- Android `APP_URL` is now `https://statistics-lover.vercel.app/app-start`.
- Android source commit: `72adfccf12f2e288e7c6b2f005e7ca12255f173b`.
- Product/version: **Statistics Lover 1.0.30**
- Android `versionCode`: **31**
- GitHub Actions run: **37183354378 — SUCCESS**
- Signed release artifact ID: **11296375705**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **669698 bytes**
- Published APK SHA-256: **`d4f21fa5e1c14b337c0880948e0a1adafe4ad83421b183fa4afe95625dd7ca5b`**
- Auto-update channel is now **1.0.30 / versionCode 31**.
- Existing website UI, session origin, single-transition recording startup, compact landscape fullscreen, Drive seek fixes, permanent signing certificate and updater lineage are preserved.


## VERIFIED RELEASE — Statistics Lover 1.0.31 unified splash + system theme

- User supplied screenshots showing two different splash states:
  - Android system splash first displayed a zoomed/masked rounded-square crop of the logo;
  - custom native overlay then switched to a different circular logo presentation.
- User also reported poor logo quality and requested automatic light/dark theme behavior across app, tablet and web.
- Logo fidelity:
  - project history contained an earlier validated original logo at **420x420**;
  - the current web/Android logo had been reduced to **240x240**;
  - restored the 420x420 original blob `4af648e1fcba3aa00ec481101b05cbe36ebc7393`.
- Splash architecture:
  - Android system SplashScreen now uses a transparent 1dp icon on the theme background, so it no longer shows a competing/masked logo;
  - the Statistics Lover logo appears only once in the native WebView loading overlay;
  - overlay uses `FIT_CENTER` instead of `CENTER_CROP`, preserving the full circular badge without zoom/crop;
  - circular clipping, subtle border and smaller 176dp presentation improve sharpness;
  - existing WebView/splash fade handoff remains.
- Native theme:
  - light mode uses `#F7F8FB` for splash/window/status/navigation backgrounds with dark system-bar icons;
  - dark mode uses `#0B1020` with light system-bar icons;
  - added `values-night/styles.xml`;
  - native shell background, WebView background, splash overlay and spinner automatically follow `Configuration.UI_MODE_NIGHT`;
  - system theme is re-applied on `uiMode` configuration changes.
- Web theme:
  - web commit `f973e87514b4df8c6efabb1bcb23689d2bdbffac` adds automatic `prefers-color-scheme` styling for public, auth, student, admin, commerce, learning and notification surfaces;
  - restored the same 420x420 original logo on web;
  - service-worker cache advanced to `statistics-lover-static-v8`;
  - theme-color meta tags now provide separate light/dark browser chrome colors;
  - Vercel deployment `dpl_Cfeg8GzJYRGoi2uHfyo6j1a3anU8` is READY and assigned to `statistics-lover.vercel.app`;
  - production `/` and `/app-start` both serve bundle `index-DaYV35YY.js`;
  - Quality run `37184284499` passed.
- Android source commit: `7c7a6b34f13980af62c7f51c05b10acd6621e410`.
- Product/version: **Statistics Lover 1.0.31**
- Android `versionCode`: **32**
- GitHub Actions run: **37184367615 — SUCCESS**
- Signed release artifact ID: **11296217436**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **672466 bytes**
- Published APK SHA-256: **`4dc85506f5576cd6351dc1ffb9d76c78f5711a4368932c60677b091697459f48`**
- Auto-update channel is now **1.0.31 / versionCode 32**.
- Existing auth-aware startup routing, website-style UI, session continuity, single-transition recording startup, compact landscape fullscreen, inline-player exit behavior and Drive seek-control stabilization remain preserved.


## RECOVERY RELEASE — Statistics Lover 1.0.32 startup rollback

- User reported that the app stopped opening after the 1.0.31 unified splash/native theme release.
- 1.0.31 compiled and signed successfully, so the failure was treated as a runtime startup regression.
- Recovery strategy: restore the exact known-working native startup/splash implementation from Android 1.0.30 while preserving the existing web deployment, package ID, signing certificate, updater lineage, recording/fullscreen fixes, seek-control fixes and auth-aware `/app-start` route.
- Restored from known-good source commit `72adfccf12f2e288e7c6b2f005e7ca12255f173b`:
  - `NativeMainActivity.java`
  - Android manifest launcher configuration
  - splash theme resources
  - original splash overlay behavior
  - pre-1.0.31 logo resource
- Removed the 1.0.31 `values-night/styles.xml` native theme override and manual native system-bar theme code from the recovery build.
- Web light/dark theme deployment remains untouched; only the Android-native startup/theme experiment was rolled back.
- Recovery source commit: `93f09e2040d92b0cef02010c27b1895df00aff97`.
- Product/version: **Statistics Lover 1.0.32**
- Android `versionCode`: **33**
- GitHub Actions run: **37185313234 — SUCCESS**
- Signed release artifact ID: **11296617602**
- Signed release APK size: **669694 bytes**
- Signed release APK SHA-256: **`9f9d75b65ee75f7f9a4d0384cbb5047783d67c24720e039a4dedf89cf8e42211`**
- Signature verification: **PASSED**.
- Self-update publication: **PASSED**.
- Auto-update channel is now **1.0.32 / versionCode 33**.
- Do not reintroduce the 1.0.31 native splash/theme changes until 1.0.32 is confirmed opening correctly on-device. Reintroduce future native theming in isolated increments only.


## VERIFIED RELEASE — Statistics Lover 1.0.34 unified splash + adaptive theme

- User confirmed the duplicate splash/logo issue remained after 1.0.32: Android first showed a zoomed/masked system-splash logo, then the custom circular logo; the Android logo was also visibly soft.
- 1.0.32 intentionally restored the known-opening 1.0.30 startup after 1.0.31 failed to open on-device. This also restored the old duplicate system-splash logo and 240x240 Android logo.
- 1.0.34 fixes those two issues **without reintroducing the 1.0.31 launcher/theme architecture**:
  - keeps the exact single-Activity startup flow proven by 1.0.32;
  - Android system splash now uses a transparent 1dp drawable and zero icon animation, so the system splash is background-only;
  - the Statistics Lover logo appears only once, in the native loading overlay;
  - Android logo asset is the validated 420x420 original from the web project (blob `4af648e1fcba3aa00ec481101b05cbe36ebc7393`);
  - overlay uses `FIT_CENTER`, transparent circular clipping, and no crop/zoom.
- Theme handling is resource-based and deliberately narrow:
  - light shell/splash/system bars: `#F7F8FB`;
  - dark shell/splash/system bars: `#0B1020`;
  - light/dark system-bar icon appearance is controlled through resource booleans;
  - spinner uses `#C6005A` light and `#FF5397` dark;
  - no `values-night/styles.xml` override is used;
  - a small runtime refresh reapplies those resources on `uiMode` changes while preserving the working Activity architecture.
- The website already follows `prefers-color-scheme` for phone/tablet/desktop and production is serving the dark-mode-capable bundle `index-DaYV35YY.js`.
- Source commits:
  - `f3b4121e7cbf5fd5e83b9110f2d51981b04f19b5` — unified splash assets/theme resources;
  - `2663cb82dd320eb4d2b945faaa12b2c216ea1195` — safe live light/dark system-bar refresh;
  - `a7ca7352ce8eedb530f6d9cdaca5a27d6f150f98` — final release bump.
- Product/version: **Statistics Lover 1.0.34**
- Android `versionCode`: **35**
- GitHub Actions run: **37186374672 — SUCCESS**
- Signed release artifact ID: **11296689615**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **672938 bytes**
- Published APK SHA-256: **`9bb92f9cc7a9557cb0aea70a2c239f3338b894419c65b929298e329cd5838a55`**
- Auto-update channel is now **1.0.34 / versionCode 35**.
- Existing auth-aware startup, website UI, session continuity, single-transition recording startup, compact landscape fullscreen, inline-player exit and Drive seek fixes remain preserved.


## VERIFIED RELEASE — Statistics Lover 1.0.35 visible splash + restored header logo

- User supplied a real-device launch recording showing two regressions after the 1.0.34 theme/splash work:
  - no visible Statistics Lover splash logo during cold launch (only the adaptive shell background appeared);
  - the website header rendered the Statistics Lover text/tagline but not the circular logo.
- Root causes:
  - the native launch overlay could receive `StatisticsLoverNative.appReady()` and fade out before Android's blank system splash had finished, so the custom circular logo never became visible;
  - the header still used the service-worker-cached public logo request, while the logo asset had been replaced during the fidelity work.
- Android fix commit: `fade6a561dbbd4420a5440f689b24f8f93de170c`.
- Product/version: **Statistics Lover 1.0.35**
- Android `versionCode`: **36**
- GitHub Actions run: **37187831151 — SUCCESS**
- Signed release artifact ID: **11298046692**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **673018 bytes**
- Published APK SHA-256: **`18837a88c4c313238d5f0a28c4cbcf649517b17fef2d6d123503b5fb4c25d9bd`**
- Auto-update channel is now **1.0.35 / versionCode 36**.
- Splash correction:
  - existing high-fidelity Statistics Lover logo remains the splash asset;
  - the native circular splash overlay now has a minimum visible duration of 1300 ms;
  - an early web `appReady()` signal schedules the reveal instead of removing the splash before it is visible;
  - WebView fade-in / splash fade-out behavior remains unchanged;
  - system splash remains iconless to avoid the earlier double-logo / mask-size jump.
- Header-logo web correction:
  - web commit `7d11ea92da7256128a92aa9ef9d01fdbd18a0f7a`;
  - logo URL is cache-busted as `/brand/statistics-lover-logo.jpg?v=20261004-2`;
  - header explicitly reserves the 52x52 logo slot and prioritizes the image request;
  - service-worker cache advanced to `statistics-lover-static-v9`;
  - Quality run `37187777649` passed;
  - Vercel deployment `dpl_DpDi4Wwwynu3UtbaMvxGKsm3fJUR` is READY and assigned to `statistics-lover.vercel.app`;
  - production JS bundle verified as `index-Cbxz-gPm.js` and contains the cache-busted logo path.
- Adaptive theme remains active:
  - Android shell/splash/status/navigation bars use DayNight `values` / `values-night` resources;
  - website uses `prefers-color-scheme: dark` overrides and light/dark theme-color metadata;
  - no manual device-specific theme fork is introduced; phone, tablet and desktop/browser follow the OS/browser preference automatically.
- Existing website UI, auth-aware startup, stable session origin, recording startup, compact landscape fullscreen, fullscreen-exit and Drive seek fixes remain preserved.


## CURRENT CHECKPOINT — splash/logo recovery and Android 1.0.37

- User reported that the splash logo and website header logo were still missing after the earlier splash/theme revisions.
- Full source audit was completed across:
  - Android launcher manifest and theme;
  - `NativeMainActivity`;
  - splash resources and logo drawables;
  - web Header component;
  - service worker cache;
  - public/bundled logo assets;
  - recent GitHub Actions and Vercel deployment history.
- Two concrete splash issues were identified:
  1. the custom splash minimum-visible timer could expire while Android's system splash was still covering the Activity;
  2. the splash ImageView used a transparent oval background with `clipToOutline`, which could make the logo disappear on-device even though the spinner/background remained visible.
- Android splash timing/mask fix commit: `08f1612e1a1cdddba69dc214e00c2c60673905b4`.
  - custom splash visibility timer now starts only after Android's system splash has actually exited;
  - splash overlay is explicitly brought to front after system splash exit;
  - circular mask now uses the theme-matched shell background instead of a fully transparent oval;
  - custom circular logo size normalized to 164dp;
  - WebView still fades in only after the web app signals readiness.
- The repository logo copies were then found to be visually degraded despite larger pixel dimensions.
- The clean original Statistics Lover image uploaded by the user in chat was recovered at **1254×1254** and is now the authoritative splash/header logo asset.
- Android original-logo commit: `1b101de64428322554afe1f637c708d25ef54491`.
- Product/version: **Statistics Lover 1.0.37**
- Android `versionCode`: **38**
- GitHub Actions run: **37189418107 — SUCCESS**
- Signed release artifact ID: **11297979731**
- Signed APK verification: **PASSED** (APK Signature Scheme v2)
- Self-update publication: **PASSED**
- Published APK size: **675774 bytes**
- Published APK SHA-256: **`1ee53781bcd0e3375f4a458768c50c292d2cea97499e6de48de1a660c84a1415`**
- Auto-update channel is now **1.0.37 / versionCode 38**.
- Existing accepted behavior remains preserved:
  - website-style UI inside APK;
  - auth-aware startup;
  - stable production session origin;
  - single-transition recording startup;
  - compact landscape fullscreen;
  - inline-player fullscreen-exit stability;
  - Drive seek/timeline stabilization;
  - permanent signing/update lineage.
- Latest signed artifact was downloaded into the active chat runtime as `Statistics-Lover-1.0.37-Release.zip`.
- Important current status:
  - Android 1.0.37 is verified and published.
  - The matching web logo commit is separate and must be verified/deployed before claiming the website header-logo fix is live.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.37

- **Statistics Lover 1.0.37 / versionCode 38** is the current authoritative Android baseline.
- Dedicated Android source commit: `1b101de64428322554afe1f637c708d25ef54491`.
- GitHub Actions Android run: **37189418107 — SUCCESS**.
- Signed release artifact ID: **11297979731**.
- Signed APK verification: **PASSED** (APK Signature Scheme v2).
- Self-update publication: **PASSED**.
- Published APK size: **675774 bytes**.
- Published APK SHA-256: **`1ee53781bcd0e3375f4a458768c50c292d2cea97499e6de48de1a660c84a1415`**.
- Auto-update channel: **1.0.37 / versionCode 38**.
- Authoritative logo source for Android splash: the clean original **1254×1254** Statistics Lover logo recovered from the user-provided image, not the degraded repository copy used in older builds.
- Keep all previously accepted behavior intact when making future Android changes:
  - website-style UI inside APK;
  - auth-aware startup;
  - stable production/session origin;
  - single-transition recording startup;
  - compact landscape fullscreen;
  - fullscreen-exit stability;
  - Drive seek/timeline stabilization;
  - permanent signing and auto-update lineage.
- Do not treat 1.0.35 or earlier splash/logo builds as the active baseline unless explicitly rolling back for diagnosis.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.38

- **Statistics Lover 1.0.38 / versionCode 39** supersedes 1.0.37 and is the current authoritative Android baseline.
- This checkpoint follows direct review of the user's screen recording showing both:
  - a blank native/custom splash logo area; and
  - a blank website header-logo slot after the page loaded.
- Confirmed root cause in 1.0.37:
  - Android `app/src/main/res/drawable-nodpi/statistics_lover_logo.jpg` used blob `ed914a2f95feed4c621066c93485e55c5241a5c4`;
  - decoded bytes did **not** begin with JPEG SOI `FF D8` and contained no valid JPEG header;
  - the same corrupt blob was also present on the production web `develop` branch at `public/brand/statistics-lover-logo.jpg` and `src/assets/statistics-lover-logo.jpg`;
  - previous 1.0.37 context claims that this blob was a valid 1254×1254 image were incorrect and are superseded by this checkpoint.
- Verified logo now used for the repair:
  - historical validated Statistics Lover JPEG blob: `4af648e1fcba3aa00ec481101b05cbe36ebc7393`;
  - valid JFIF/JPEG header;
  - actual dimensions: **420×420**;
  - size: **14997 bytes**.
- Android repair source commit: `21936a84f2eec03febe48f23816801d893902390` — `fix: restore valid splash logo and align system splash 1.0.38`.
- Android changes:
  - replaced the corrupt packaged splash/logo binary with the verified 420×420 JPEG;
  - system splash now uses `@drawable/statistics_lover_logo` instead of `@drawable/splash_blank`;
  - custom splash continues to use the same verified logo;
  - custom splash logo size normalized to 140dp;
  - preserved existing auth-aware startup, website-style UI, stable production origin, recording startup, fullscreen, Drive seek and permanent signing/update behavior.
- GitHub Actions run: **37190376246 — SUCCESS**.
- Signed release build step: **SUCCESS**.
- Signed release verification step: **SUCCESS**.
- Self-update publication step: **SUCCESS**.
- Release artifact ID: **11297594955**.
- Published APK commit: `f83c2e3bd4981cb14b2e8993def58143fa9ecf1a` — `Publish Statistics Lover APK 1.0.38`.
- Published APK size: **675386 bytes**.
- Published APK SHA-256: **`151c0416e518787a808850097b4cfc19a4b2053b27bbda480f69a139b0d15be6`**.
- Auto-update channel: **1.0.38 / versionCode 39**.
- Production web/logo repair:
  - Vercel deploys the `develop` branch for the active application flow;
  - web fix commit: `3415a5aed797a123b533f912966e0acce828e5bc` — `fix: replace corrupt logo blobs and invalidate caches`;
  - both public and bundled header-logo files were replaced with valid blob `4af648e1fcba3aa00ec481101b05cbe36ebc7393`;
  - service-worker cache advanced to `statistics-lover-static-v12`;
  - public logo references advanced to `?v=20261004-4`;
  - manifest icon size metadata corrected to 420×420;
  - Vercel deployment `dpl_44Pj3N988Gb3BkdSo5rdxnLJDg35` is **READY**;
  - canonical APK/web origin `statistics-lover.vercel.app` was remapped from old deployment `dpl_GRwRhyfLRse6w7iSFyxvNjt3GirH` to the fixed deployment;
  - live canonical HTML returns the new `?v=20261004-4` references and the live logo endpoint returns HTTP 200 with `image/jpeg`, JFIF bytes, and content-length 14997.
- Verification boundary:
  - repository asset integrity, Android build/signature workflow, auto-update publication, Vercel deployment and live served logo bytes are verified;
  - final on-device visual confirmation of the native splash and rendered header logo is pending installation/opening of 1.0.38 by the user.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.39

- **Statistics Lover 1.0.39 / versionCode 40** supersedes 1.0.38 and is the current authoritative Android baseline.
- This checkpoint follows direct frame-by-frame review of user recording `1000327059.mp4`.
- Recording-confirmed failures in 1.0.38:
  - launcher icon was still the old chart vector;
  - Android displayed a separate chart-style system splash before the intended loading screen;
  - the custom circular splash showed a blank circle/spinner instead of the logo;
  - the website header showed a blank circular logo slot.
- Root-cause correction:
  - the 420×420 JPEG blob `4af648e1fcba3aa00ec481101b05cbe36ebc7393` had a JPEG/JFIF header but the actual packaged file was structurally damaged;
  - strict decoding of the 1.0.38 APK copy failed in PIL with `broken data stream` and OpenCV could not decode it;
  - tolerant preview renderers could display it, which previously caused the asset to be incorrectly treated as healthy;
  - the clean pre-corruption 240×240 JPEG blob `86549c806ec54b82cbe8765082cffcc7a0a4c28f` is now the authoritative logo source until a new higher-resolution original is supplied.
- Startup architecture was simplified from two visible layers to one:
  - removed the custom Android launch overlay, spinner and `clipToOutline` logo rendering path;
  - Android `SplashScreen` is now the only startup splash;
  - `setKeepOnScreenCondition(() -> !webUiReady)` holds that single branded splash until the web UI is ready;
  - the WebView is made visible before the splash is released, avoiding an intermediate blank frame;
  - splash exit uses only a short fade;
  - no second circular splash exists after the Android splash.
- App icon:
  - `android:icon` and `android:roundIcon` now point directly to `@drawable/statistics_lover_logo`;
  - the old chart `ic_launcher.xml` is no longer used by the application manifest.
- Android source commits:
  - `ca7bd80b5bf79065abb4befeb8446538c1471f30` — `fix: single branded splash and logo launcher icon 1.0.39`;
  - `6937acad9c4a39e90bf689b114edc7822c0d1578` — `fix: complete single-splash cleanup 1.0.39`.
- First 1.0.39 build attempt `37192026139` failed because two old-overlay references remained; the exact compiler failures were corrected before release.
- Final GitHub Actions run: **37192112231 — SUCCESS**.
- Debug build and verification: **SUCCESS**.
- Signed release build: **SUCCESS**.
- Signed release verification: **SUCCESS**.
- Self-update publication: **SUCCESS**.
- Release artifact ID: **11298779778**.
- Auto-update channel: **1.0.39 / versionCode 40**.
- Published APK size: **672758 bytes**.
- Published APK SHA-256: **`5ed9be4333eb88950c0cd05c8dfe444fe0e0ff4dc9511ab303206c02ff1a3b82`**.
- Binary verification performed on the signed release artifact:
  - packaged logo entry is 13085 bytes;
  - JFIF baseline JPEG, actual dimensions 240×240;
  - PIL strict decode: **PASSED**;
  - OpenCV decode: **PASSED**.
- Matching web/header repair:
  - web `develop` commit `faa50b124acbed7c48fa02aef3dbdba54e8decad` — `fix: replace damaged logo with clean cached-safe asset`;
  - new public/header asset name: `statistics-lover-logo-clean.jpg`;
  - service-worker cache: `statistics-lover-static-v13`;
  - cache-bust generation: `?v=20261004-5`;
  - Vercel deployment `dpl_C3iFuqcRcwxsuxugG5zxcCwU9ZA6` is READY;
  - canonical `statistics-lover.vercel.app` is mapped to that deployment;
  - live HTML references the clean logo;
  - production JS header uses `brand-logo` with bundled asset `/assets/statistics-lover-logo-clean-Dqadk_XN.jpg`;
  - bundled asset returns HTTP 200, `image/jpeg`, content-length 13085 and JFIF bytes.
- Final on-device visual confirmation is still required after the user installs/opens 1.0.39; repository, build, signing, binary image decoding, website deployment and live asset serving are verified.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.40

- **Statistics Lover 1.0.40 / versionCode 41** supersedes 1.0.39 as the current authoritative Android baseline.
- This is a splash-only refinement based on direct review of the user's latest recording.
- All other 1.0.39 behavior remains intentionally unchanged: launcher icon, website header logo, routing, WebView behavior, fullscreen behavior, update flow, auth/startup logic and production origin.
- Recording-confirmed issue in 1.0.39:
  - Android's system splash masked/cropped the square Statistics Lover logo to the Android splash-icon safe area;
  - the outer circular ring and lower/side logo artwork were visibly cut.
- Fix:
  - added dedicated `@drawable/statistics_lover_splash`;
  - the splash drawable uses a larger transparent canvas with the clean Statistics Lover logo inset and centered;
  - `windowSplashScreenAnimatedIcon` now references the dedicated splash drawable instead of using the application logo directly;
  - app icon continues to use `@drawable/statistics_lover_logo` unchanged.
- Android source commit: `f61d4d53af1dffc9029794b37790cc57c60f65a3` — `fix: keep full logo inside Android splash safe area 1.0.40`.
- GitHub Actions run: **37192632223 — SUCCESS**.
- Debug build and verification: **SUCCESS**.
- Signed release build and verification: **SUCCESS**.
- Self-update publication: **SUCCESS**.
- Release artifact ID: **11300090265**.
- Published APK size: **673362 bytes**.
- Published APK SHA-256: **`538a4f086dcedb690cdf012777fbb32d2d4caa98e4d1bfe5dd53f6770ac935d6`**.
- Auto-update channel: **1.0.40 / versionCode 41**.
- Final on-device confirmation of the new splash framing is still required after installation.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.41

- **Statistics Lover 1.0.41 / versionCode 42** supersedes 1.0.40 as the current authoritative Android baseline.
- This release is the completed high-quality splash correction requested after the user supplied the original Statistics Lover logo.
- Working 1.0.40 behavior was intentionally preserved; the change is limited to the splash asset/package path plus release verification plumbing.
- Final splash asset:
  - dedicated committed resource: `app/src/main/res/drawable-nodpi/statistics_lover_splash_hd.webp`;
  - high-quality circular Statistics Lover artwork;
  - padded specifically for Android splash safe-area masking;
  - no rounded-square framing;
  - no reuse of the old low-resolution/corrupt JPEG splash source.
- Splash theme uses `@drawable/statistics_lover_splash_hd`.
- Final packaging clean-up:
  - removed temporary Base64 staging files;
  - removed fragile APK ZIP filename-grep verification that falsely failed after Android resource shrinking/renaming;
  - committed the actual binary resource directly into the Android source tree;
  - fixed an intermediate accidental `statistics_lover_splash_hd_hd` XML reference before final release.
- Final source/publish sequence:
  - `c792e3cb8d7fb2d6fd1f0476292149dc9222651c` — finalized HD splash packaging;
  - `32f632ee3a6880114f62f7f8c157eaacbfa4422d` / follow-up no-op duplicate — corrected splash resource reference;
  - `afa64a91b4315537ee35bcfd2dcde0f1b821aefe` — forced clean head build for publication;
  - `ae3f0f7dd8b6c8398db8dd743a171be223912680` — `Publish Statistics Lover APK 1.0.41`.
- Final GitHub Actions run: **37196318084 — SUCCESS**.
- Committed HD splash verification: **SUCCESS**.
- Debug build and verification: **SUCCESS**.
- Signed release build and APK signature verification: **SUCCESS**.
- Release artifact upload: **SUCCESS**.
- Self-update publication: **SUCCESS**.
- Auto-update channel: **1.0.41 / versionCode 42**.
- Published APK size: **703351 bytes**.
- Published APK SHA-256: **`69c31479d1eed910e71ee838023446dd00c08fb52a433c75d399c6dd9f8e3c31`**.
- Final release artifact ID: **11300433697**.
- On-device visual confirmation of the new circular splash is the only remaining verification step.


## CURRENT AUTHORITATIVE ANDROID BASELINE — Statistics Lover 1.0.41

- **Statistics Lover 1.0.41 / versionCode 42** supersedes 1.0.40 and is the current authoritative Android baseline.
- Scope of this release is intentionally limited to the splash image. The working 1.0.40 launcher icon, website/header logo, routing, WebView behavior, fullscreen behavior, auth/startup logic, production origin and update flow are unchanged.
- User supplied the original Statistics Lover circular logo and requested a high-quality truly circular splash.
- Final splash resource:
  - path: `app/src/main/res/drawable-nodpi/statistics_lover_splash_hd.webp`;
  - dimensions: **384×384 RGBA**;
  - transparent canvas;
  - circular emblem alpha bounds: **(67, 67)–(317, 317)**, i.e. 250×250 content centered on the 384×384 canvas (~65% width) to stay within Android splash safe-area masking;
  - file size: **30312 bytes**;
  - SHA-256: **`f33020e716bb1e7d71b4a95f04886277bb12170dc7ee355d80a1a3a30ede22f1`**.
- Splash theme now resolves `windowSplashScreenAnimatedIcon` to `@drawable/statistics_lover_splash_hd`.
- Release build integrity safeguards:
  - workflow verifies the committed HD splash SHA-256 before building;
  - signed APK verification uses Android APK Signature Scheme v2;
  - `aapt2 dump resources` verifies that the compiled APK contains `drawable/statistics_lover_splash_hd` and that `windowSplashScreenAnimatedIcon` resolves to it.
- Final successful GitHub Actions run: **37196318084 — SUCCESS**.
- Final source/build head: `afa64a91b4315537ee35bcfd2dcde0f1b821aefe` — `ci: publish finalized 1.0.41 splash build`.
- Published APK commit: `ae3f0f7dd8b6c8398db8dd743a171be223912680` — `Publish Statistics Lover APK 1.0.41`.
- Release artifact ID: **11300433697**.
- Published APK size: **703351 bytes**.
- Published APK SHA-256: **`69c31479d1eed910e71ee838023446dd00c08fb52a433c75d399c6dd9f8e3c31`**.
- Auto-update channel: **1.0.41 / versionCode 42**.
- Post-build binary verification:
  - downloaded the final signed release artifact;
  - Android resource optimizer renamed the splash file internally to `res/Td.webp`;
  - extracted `res/Td.webp` is **30312 bytes**, **384×384 RGBA**, alpha bounds **(67,67)–(317,317)**;
  - its SHA-256 is exactly **`f33020e716bb1e7d71b4a95f04886277bb12170dc7ee355d80a1a3a30ede22f1`**, proving the approved circular splash was packaged byte-for-byte into the final signed APK.
- Final on-device visual confirmation remains the only pending check after the user installs/opens 1.0.41.
