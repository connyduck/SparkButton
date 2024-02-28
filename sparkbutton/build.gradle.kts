plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
    signing
}

android {
    namespace = "at.connyduck.sparkbutton"

    compileSdk = 34

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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "at.connyduck.sparkbutton"
                artifactId = "sparkbutton"
                version = "4.2.0"

                from(components.findByName("release"))

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
                        developerConnection.set("scm:git:ssh://github.com/connyduck/sparkbutton.git")
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
    implementation(libs.androidx.appcompat)
}