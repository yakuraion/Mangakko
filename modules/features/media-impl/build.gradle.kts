import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    addCommon()
    api(project(":modules:features:media"))
    implementation(project(":modules:core-feature"))

    testImplementation(project(":modules:core-testutils"))
    testImplementation(Libraries.archTest)
}
