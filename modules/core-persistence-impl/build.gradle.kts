import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    api(project(":modules:core-persistence"))
    implementation(project(":modules:core-di"))
    implementation(Libraries.gson)
}
