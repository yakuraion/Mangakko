import Libraries.addCommon

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    addCommon()

    api(project(":modules:core-uikit"))
    api(project(":modules:core-di"))
    api(project(":modules:core-repositories"))

    api(Libraries.viewModelKtx)
    api(Libraries.viewModelSavedState)
    api(Libraries.liveDataKtx)
}
