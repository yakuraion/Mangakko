import Libraries.addCommon
import Libraries.addRoom

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    implementation(project(":modules:core-utils"))
    addRoom()
    api(project(":modules:core-persistence"))
    implementation(project(":modules:core-di"))
    implementation(Libraries.gson)
}
