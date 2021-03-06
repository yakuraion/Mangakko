import Libraries.addCommon
import Libraries.addHyperion
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

val localProperties = loadProperties("${rootDir}/local.properties")
val versionProperties = loadProperties("${rootDir}/version.properties")

android {
    defaultConfig {
        applicationId = "net.yakuraion.mangakko"
        versionCode = versionProperties.getProperty("versionCode").toInt()
        versionName = versionProperties.getProperty("versionName")

        buildConfigField("String", "SERVER_URL", "\"https://graphql.anilist.co/\"")
    }

    packagingOptions {
        exclude("build-data.properties")
    }

    signingConfigs {
        create("debugConfig") {
            storeFile = File("$rootDir/keystores/debug_keystore.jks")
            storePassword = "debugpassword"
            keyAlias = "debugkey"
            keyPassword = "debugpassword"
        }

        create("releaseConfig") {
            storeFile = File("$rootDir/keystores/release_keystore.jks")
            storePassword = localProperties.getProperty("release_password")
            keyAlias = "releasekey"
            keyPassword = localProperties.getProperty("release_password")
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debugConfig")
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("releaseConfig")
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("environment")

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Mangakko (Dev)")
        }

        create("prod") {
            dimension = "environment"
            resValue("string", "app_name", "Mangakko")
        }
    }

    applicationVariants.all {
        outputs.forEach { output ->
            if (output is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                output.outputFileName = "${applicationId}-v$versionName-$versionCode.${output.outputFile.extension}"
            }
        }
    }
}

dependencies {
    addCommon()
    implementation(project(":modules:core-utils"))
    addHyperion()
    implementation(Libraries.gson)

    debugImplementation(Libraries.leakCanary)

    implementation(project(":modules:core-di"))
    implementation(project(":modules:core-uikit"))
    implementation(project(":modules:core-repositories-impl"))
    implementation(project(":modules:core-network-impl"))
    implementation(project(":modules:core-persistence-impl"))
    implementation(project(":modules:core-feature"))

    implementation(project(":modules:features:main-impl"))
    implementation(project(":modules:features:pager-impl"))
    implementation(project(":modules:features:media-impl"))
    implementation(project(":modules:features:favorites-impl"))
    implementation(project(":modules:features:settings-impl"))
}
