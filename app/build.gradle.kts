@file:Suppress("SpellCheckingInspection")

import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.imashnake.animite"
        minSdk = 26
        targetSdk = 31
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

apollo {
    packageName.set("com.imashnake.animite")
}

// TODO:
//  This is a temporary workaround, remove this once Apollo Kotlin 3.1.1 is out, see:
//  https://github.com/jmfayard/refreshVersions/issues/507.
tasks.configureEach {
    if (name == "checkApolloVersions") {
        enabled = false
    }
}

dependencies {
    // AndroidX
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.navigation.compose)

    // Compose
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.compose.runtime)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.toolingPreview)

    // Apollo Kotlin
    implementation("com.apollographql.apollo3:apollo-runtime:_")

    // Coil
    implementation(COIL.compose)

    // Kotlin coroutines TODO: BoM?
    implementation(KotlinX.coroutines.android)
    implementation(KotlinX.coroutines.core)

    // Kotlin
    implementation(kotlin("reflect"))

    testImplementation(Testing.junit4)

    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)

    debugImplementation(AndroidX.compose.ui.tooling)
}