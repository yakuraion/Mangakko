buildscript {

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.detektGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

plugins {
    kotlin("android") version kotlinVersion apply false
}

subprojects {
    apply {
        from("${rootDir}/detekt.gradle")
    }
    plugins.withType<com.android.build.gradle.internal.plugins.BasePlugin<*, *>> {
        configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(AndroidSdk.compile)
            defaultConfig {
                minSdkVersion(AndroidSdk.min)
                targetSdkVersion(AndroidSdk.target)
            }
            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }

            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }
            }

            if (displayName.contains(":features:")) {
                resourcePrefix(name.removeSuffix("-impl").replace('-', '_') + '_')
            }
        }
    }
}
