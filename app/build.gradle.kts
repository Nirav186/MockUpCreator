plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "mockupmaker.screenshots.mockup.generator"
    compileSdk = 34

    defaultConfig {
        applicationId = "mockupmaker.screenshots.mockup.generator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        // Enabling multidex support.
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.6.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.2")
    implementation("androidx.compose.material:material:1.6.2")
    implementation("androidx.lifecycle:lifecycle-process:2.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.2")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.6.2")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:1.6.2")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("com.google.accompanist:accompanist-flowlayout:0.15.0")

    implementation("net.lingala.zip4j:zip4j:2.11.3")

    // Color sheet
    implementation("io.github.vanpra.compose-material-dialogs:color:0.9.0")
    implementation("com.raedapps:alwan:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-config:21.6.1")
    implementation("com.google.firebase:firebase-common-ktx:20.4.2")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("com.github.Nirav186:commons:0.2.5")
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}