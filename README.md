# 8BP Ultimate Assistant

Android starter repository for a compact, draggable gaming overlay and **standalone billiards geometry tools**.

## Current scope

- Jetpack Compose dashboard with Aim Guide, Trajectory, Bank Shot, Power Assist and Table Analysis sections.
- Floating overlay service using Android's `SYSTEM_ALERT_WINDOW` permission.
- Pure Kotlin geometry helpers for straight-line aim, circle collision, cushion reflection and basic power estimation.
- Clear extension points for adding a **user-provided table state**.

## Important limitations

This repository does **not** read or modify 8 Ball Pool's process, inspect game memory, bypass anti-cheat, or automate shots in Miniclip online matches. It does not claim to detect live ball positions automatically. Android does not provide another app's internal game state to an overlay. Live visual recognition normally requires screen capture (MediaProjection) or an authorized game integration; this starter intentionally does not capture the screen.

The planner is a local geometry utility. Automatic shot execution is intentionally not implemented for online matches. Use automation only in environments that explicitly permit bots and provide an authorized control interface.

## Build

1. Open this repository in Android Studio (Ladybug or newer).
2. Allow Gradle to sync.
3. Connect an Android device or start an emulator (minSdk 26).
4. Run the `app` configuration.

Command line:

```bash
./gradlew assembleDebug
```

The APK is produced at `app/build/outputs/apk/debug/app-debug.apk`.

## Overlay permission

Tap **Enable overlay** in the app and grant "Display over other apps" in Android Settings. Return to the app and tap **Show overlay**. Android may restrict overlays over secure windows or certain system surfaces.

## Project structure

```text
app/src/main/java/com/example/eightbp/
  MainActivity.kt
  overlay/OverlayService.kt
  geometry/BilliardsGeometry.kt
  ui/AssistantScreen.kt
```

## Next development steps

- Add a calibration screen where users manually enter table bounds and ball coordinates.
- Add tests for collision and cushion geometry.
- If adding computer vision, use an explicitly authorized image source and obtain all required Android permissions transparently.
