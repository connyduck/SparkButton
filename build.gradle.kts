buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("io.github.gradle-nexus:publish-plugin:1.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.5.1")
    }
}

apply(plugin = "io.github.gradle-nexus.publish-plugin")

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
