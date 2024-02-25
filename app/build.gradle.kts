plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "at.connyduck.sparkbutton.sample"
    compileSdk = 33

    defaultConfig {
        applicationId = "at.connyduck.sparkbutton.sample"
        minSdk = 21
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        abortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation(project(":sparkbutton"))
    implementation(project(":sparkbutton-compose"))

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.cardview:cardview:1.0.0")

    implementation("androidx.compose.foundation:foundation:1.4.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
}
