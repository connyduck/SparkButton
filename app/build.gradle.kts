plugins {
    id("com.android.application")
}

android {
    namespace = "at.connyduck.sparkbutton.sample"
    compileSdk = 33

    defaultConfig {
        applicationId = "at.connyduck.sparkbutton.sample"
        minSdk = 19
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(project(":sparkbutton"))

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.cardview:cardview:1.0.0")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
}


