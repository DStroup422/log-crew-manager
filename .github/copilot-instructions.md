# AI Coding Guidelines for log-crew-manager

## Project Overview

This is an Android app for logging crew management in the forestry/logging industry. It tracks workers, loads, locations, and property boundaries using Room database, Jetpack Compose UI, and Google Maps visualization.

## Architecture

- **Single Activity Architecture**: MainActivity hosts the entire app using Jetpack Compose
- **Data Layer**: Room entities for WorkerEntity, LocationEntity, LoadEntity, PropertyEntity, PropertyPointEntity
- **Business Logic**: PayCalculator for worker pay distribution, GeoUtils for geospatial calculations
- **UI**: Compose-based MapScreen displaying properties as polygons and locations as markers

## Key Patterns

- **Entity Definitions**: All Room entities are defined in `MainActivity.kt` (unconventional - typically in separate data/model files)
- **Utility Objects**: Business logic encapsulated in object singletons (PayCalculator, GeoUtils)
- **Package Structure**: `com.loggingapp` namespace
- **Compose Integration**: Direct integration of Google Maps with Compose using `maps-compose` library

## Build & Run

- **Prerequisites**: JDK 17+, Android SDK with `ANDROID_SDK_ROOT` set
- **Setup Android SDK**:
  - Download command line tools: `wget https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip`
  - Extract to `~/Android/Sdk/cmdline-tools/latest/`
  - Accept licenses: `~/Android/Sdk/cmdline-tools/latest/bin/sdkmanager --licenses`
  - Install components: `sdkmanager "platform-tools" "build-tools;33.0.2" "platforms;android-33"`
  - Set `ANDROID_SDK_ROOT=~/Android/Sdk`
- **Build Command**: `./gradlew assembleDebug` (create wrapper if needed)
- **Install**: `adb install -r app/build/outputs/apk/debug/app-debug.apk`
- **IDE**: Android Studio for full development (syncs Gradle automatically)

## Dependencies & Versions

- **Gradle Plugin**: 8.1.0
- **Kotlin**: 1.8.10
- **Compose**: 1.4.3 (BOM)
- **Maps**: play-services-maps 18.1.0, maps-compose 2.11.0
- **Room**: 2.5.0

## Common Tasks

- **Add New Entity**: Define @Entity data class in MainActivity.kt, update Room version if schema changes
- **Map Features**: Use `GoogleMap` composable with `Polygon` for boundaries, `Marker` for points
- **Database Operations**: Implement DAOs in separate files (none currently exist - add as needed)
- **API Key**: Replace `YOUR_API_KEY_HERE` in AndroidManifest.xml for Maps functionality

## Code Style

- **Language**: Kotlin only
- **UI Framework**: Jetpack Compose (no XML layouts)
- **Data Classes**: Immutable entities with auto-generated IDs
- **Geospatial**: LatLng coordinates, polygon point-in-polygon checks</content>
  <parameter name="filePath">/workspaces/log-crew-manager/.github/copilot-instructions.md
