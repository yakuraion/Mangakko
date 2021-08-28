import Libraries.addCommon
import Libraries.apiApollo

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    api(project(":modules:core-network"))
    implementation(project(":modules:core-di"))
    implementation(project(":modules:core-persistence"))
}
