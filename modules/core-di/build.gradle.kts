import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    implementation(Libraries.gson)

    implementation(project(":modules:core-repositories"))
    implementation(project(":modules:core-network"))
    implementation(project(":modules:core-persistence"))

    implementation(project(":modules:features:main"))
    implementation(project(":modules:features:pager"))
    implementation(project(":modules:features:genres"))
    implementation(project(":modules:features:media"))
}
