import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    implementation(Libraries.coroutinesTest)
    implementation(Libraries.archTest)

    implementation(project(":modules:core-di"))
}
