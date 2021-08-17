import Libraries.addCommon
import Libraries.apiFastAdapter

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    addCommon()

    api(project(":modules:core-entity"))

    api(Libraries.appCompat)
    api(Libraries.fragmentKtx)

    api(Libraries.material)
    api(Libraries.constraintLayout)
    api(Libraries.recyclerView)
    api(Libraries.viewPager2)
    api(Libraries.shimmer)

    apiFastAdapter()

    api(Libraries.glide)
}
