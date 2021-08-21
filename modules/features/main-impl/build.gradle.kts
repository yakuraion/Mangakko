import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    addCommon()
    api(project(":modules:features:main"))
    implementation(project(":modules:core-feature"))

    implementation(project(":modules:features:genres"))
}
