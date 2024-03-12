plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka) apply false
    id("io.github.gradle-nexus.publish-plugin") version libs.versions.publishPlugin
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.layout.buildDirectory)
}

apply(from = "$rootDir/scripts/publish-root.gradle")
