plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ru.yotfr.unisoldevtest"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.yotfr.unisoldevtest"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:connectivity"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:downloader"))
    implementation(project(":core:installer"))
    implementation(project(":core:resources"))
    implementation(project(":core:memorycache"))
    implementation(project(":data:categories"))
    implementation(project(":data:categorywallpapers"))
    implementation(project(":data:favoritewallpapers"))
    implementation(project(":data:wallpaperdownloads"))
    implementation(project(":data:wallpaperinstaller"))
    implementation(project(":data:shared"))
    implementation(project(":data:settings"))
    implementation(project(":data:storageloader"))
    implementation(project(":data:wallpaperdetails"))
    implementation(project(":domain:categories"))
    implementation(project(":domain:categorywallpapers"))
    implementation(project(":domain:favoritewallpapers"))
    implementation(project(":domain:settings"))
    implementation(project(":domain:shared"))
    implementation(project(":domain:storageloader"))
    implementation(project(":domain:wallpaperdetails"))
    implementation(project(":domain:wallpaperdownloads"))
    implementation(project(":domain:wallpaperinstaller"))
    implementation(project(":feature:categories"))
    implementation(project(":feature:categorywallpapers"))
    implementation(project(":feature:favoritewallpapers"))
    implementation(project(":feature:home"))
    implementation(project(":feature:savedwallpapers"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:shared"))
    implementation(project(":feature:wallpaperdetails"))

    dataStore()
    accompanist()
    coil()
    paging()
    retrofit()
    room()
    coroutinesCore()
    coroutinesAndroid()
    navigation()
    hilt()
    activityCompose()
    compose()
    test()
    viewModelKtx()
    coreKtx()
    runtimeKtx()
}