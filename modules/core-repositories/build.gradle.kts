import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()
    api(Libraries.paging)
    api(project(":modules:core-entity"))
}
