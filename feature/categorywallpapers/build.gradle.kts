plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "ru.yotfr.categorywallpapers"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain:categorywallpapers"))
    implementation(project(":domain:favoritewallpapers"))
    implementation(project(":core:resources"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:shared"))
    implementation(project(":domain:shared"))

    coil()
    paging()
    coroutinesCore()
    coroutinesAndroid()
    navigation()
    navigationHiltExt()
    hilt()
    compose()
    viewModelKtx()
    coreKtx()
    runtimeKtx()
}