plugins {
    alias(libs.plugins.android.application)
    kotlin("android")
    alias(libs.plugins.ktlint)
}

android {
    namespace = "at.connyduck.sparkbutton.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "at.connyduck.sparkbutton.sample"
        minSdk = 21
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":sparkbutton"))
    implementation(project(":sparkbutton-compose"))

    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.cardview)

    implementation(libs.androidx.compose.foundation)

    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.rules)
}
