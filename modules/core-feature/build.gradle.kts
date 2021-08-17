import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()

    api(project(":modules:core-ui"))
    api(project(":modules:core-di"))

    api(Libraries.viewModelKtx)
    api(Libraries.viewModelSavedState)
    api(Libraries.liveDataKtx)
}
