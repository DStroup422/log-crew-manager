# log-crew-manager — Build instructions

This workspace contains a minimal Android app skeleton. To build:

1. Install JDK 11+, Android SDK, and ensure `ANDROID_SDK_ROOT` is set.
2. Open the project in Android Studio and let it sync.
3. Or build from the command line with an installed Gradle:

```bash
# from repository root
./gradlew assembleDebug    # if wrapper available
# or
gradle assembleDebug
```

To install to a device/emulator:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```