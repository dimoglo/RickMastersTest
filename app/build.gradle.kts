plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
    id ("kotlin-android")
    id ("kotlinx-serialization")
    id ("io.realm.kotlin")
}

android {
    namespace = "com.example.rickmasterstestdev"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rickmasterstest"
        minSdk = 26
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Compose
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.material3:material3-android:1.2.0")
    implementation ("androidx.compose.material:material-icons-extended:1.6.0")
    implementation ("androidx.compose.foundation:foundation:1.6.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    runtimeOnly ("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-common:1.9.10")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.10")

    //LifeCycle
    implementation ("androidx.lifecycle:lifecycle-common:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    annotationProcessor ("com.google.dagger:hilt-compiler:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-android:2.3.7")
    implementation("io.ktor:ktor-serialization:2.3.7")
    implementation("io.ktor:ktor-client-serialization:2.3.7")
    implementation("io.ktor:ktor-client-logging:2.3.7")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Realm
    implementation("io.realm.kotlin:library-base:1.13.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Utils
    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation ("com.airbnb.android:lottie:5.2.0")

    //Coil
    implementation ("io.coil-kt:coil-compose:2.4.0")
    implementation ("io.coil-kt:coil-svg:2.4.0")
}

kapt {
    correctErrorTypes = true
}