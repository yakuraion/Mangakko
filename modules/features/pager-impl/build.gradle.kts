import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    addCommon()
    api(project(":modules:features:pager"))
    implementation(project(":modules:core-feature"))

    implementation(project(":modules:features:media"))
    implementation(project(":modules:features:favorites"))
    implementation(project(":modules:features:settings"))
}
