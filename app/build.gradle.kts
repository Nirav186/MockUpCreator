plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mobileappxperts.mockupgenerator.mockupmaker"
    signingConfigs {
        create("release") {
            storePassword = "com.mobileappxperts.mockupgenerator.mockupmaker"
            keyPassword = "com.mobileappxperts.mockupgenerator.mockupmaker"
            storeFile = file("D:\\Projects\\mockupcreator\\mockupcreator.jks")
            keyAlias = "key0"
        }
    }
    compileSdkVersion(34)

    defaultConfig {
        applicationId = "com.mobileappxperts.mockupgenerator.mockupmaker"
        minSdkVersion(23)
        targetSdkVersion(34)
        versionCode = 4
        versionName = "1.0.3"

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
            signingConfig = signingConfigs["release"]
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
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0-rc02")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-rc02")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.lifecycle:lifecycle-process:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-rc02")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-rc02")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-rc02")

    // Gson
    implementation("com.google.code.gson:gson:2.9.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.3.3")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:1.3.1")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Room
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("com.google.accompanist:accompanist-flowlayout:0.15.0")

    implementation("net.lingala.zip4j:zip4j:2.11.3")

    // Color sheet
    implementation("io.github.vanpra.compose-material-dialogs:color:0.9.0")
    implementation("com.raedapps:alwan:1.0.0")

    implementation("com.google.android.gms:play-services-ads:22.0.0")

    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-config:21.4.1")
    implementation("com.google.firebase:firebase-common-ktx:20.3.3")
    implementation("androidx.navigation:navigation-compose:2.7.6")
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}