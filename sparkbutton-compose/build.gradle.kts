plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
    signing
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka)
}

android {
    namespace = "at.connyduck.sparkbutton.compose"

    compileSdk = 33

    defaultConfig {
        minSdk = 19
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    lint {
        abortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml.get().outputDirectory.get())
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "at.connyduck.sparkbutton"
                artifactId = "sparkbutton-compose"
                version = "1.0.0-alpha1"

                from(components.findByName("release"))
                artifact(javadocJar.get())

                pom {
                    name.set("SparkButton")
                    description.set("Android library to create buttons with sparkly animation.")
                    url.set("https://github.com/connyduck/SparkButton")

                    licenses {
                        license {
                            name.set("Apache-2.0 License")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("connyduck")
                            name.set("Konrad Pozniak")
                            email.set("opensource@connyduck.at")
                        }
                    }

                    scm {
                        connection.set("scm:git:github.com/connyduck/sparkbutton.git")
                        developerConnection.set(
                            "scm:git:ssh://github.com/connyduck/sparkbutton.git"
                        )
                        url.set("https://github.com/connyduck/SparkButton")
                    }
                }
            }
        }
    }
}

signing {
    val signingKeyId = rootProject.ext["signing.keyId"] as String?
    val signingKey = rootProject.ext["signing.key"] as String?
    val signingPassword = rootProject.ext["signing.password"] as String?
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications)
}

dependencies {
    implementation(libs.androidx.compose.foundation)
}
