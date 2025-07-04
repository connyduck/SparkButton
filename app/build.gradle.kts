plugins {
    alias(libs.plugins.android.application)
    kotlin("android")
    alias(libs.plugins.ktlint)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "at.connyduck.sparkbutton.sample"
    compileSdk = 36

    defaultConfig {
        applicationId = "at.connyduck.sparkbutton.sample"
        minSdk = 21
        targetSdk = 36
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        abortOnError = false
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":sparkbutton"))
    implementation(project(":sparkbutton-compose"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
