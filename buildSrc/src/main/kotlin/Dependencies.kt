@file:Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")

import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.4.32"
const val apolloVersion = "2.5.9"

object AndroidSdk {

    const val min = 23
    const val compile = 30
    const val target = compile
}

object BuildPlugins {

    object Versions {

        const val buildTools = "4.1.3"
        const val detekt = "1.16.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"
}

object Libraries {

    object Versions {

        const val coroutines = "1.4.3"

        const val appCompat = "1.2.0"
        const val fragment = "1.3.2"
        const val coreKtx = "1.5.0-rc01"

        const val dagger = "2.33"

        const val timber = "4.7.1"

        const val lifecycle = "2.3.1"

        const val room = "2.3.0"

        const val material = "1.3.0"
        const val constraintLayout = "2.0.4"
        const val recyclerView = "1.1.0"
        const val viewPager2 = "1.0.0"
        const val shimmer = "0.5.0"

        const val paging = "2.1.2"

        const val fastAdapter = "5.4.1"

        const val glide = "4.12.0"

        const val gson = "2.8.6"

        const val jodaTime = "2.10.9.1"

        const val hyperion = "0.9.33"

        const val leakCanary = "2.7"

        const val junit = "4.13.2"
        const val mockitoKotlin = "3.2.0"

        const val archTest = "2.1.0"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

    const val fastAdapter = "com.mikepenz:fastadapter:${Versions.fastAdapter}"
    const val fastadapterExpandable = "com.mikepenz:fastadapter-extensions-expandable:${Versions.fastAdapter}"
    const val fastadapterDiff = "com.mikepenz:fastadapter-extensions-diff:${Versions.fastAdapter}"
    const val fastadapterDrag = "com.mikepenz:fastadapter-extensions-drag:${Versions.fastAdapter}"
    const val fastadapterPaged = "com.mikepenz:fastadapter-extensions-paged:${Versions.fastAdapter}"
    const val fastadapterScroll = "com.mikepenz:fastadapter-extensions-scroll:${Versions.fastAdapter}"
    const val fastadapterSwipe = "com.mikepenz:fastadapter-extensions-swipe:${Versions.fastAdapter}"
    const val fastadapterUI = "com.mikepenz:fastadapter-extensions-ui:${Versions.fastAdapter}"
    const val fastadapterUtils = "com.mikepenz:fastadapter-extensions-utils:${Versions.fastAdapter}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val jodaTime = "net.danlew:android.joda:${Versions.jodaTime}"

    const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:${Versions.hyperion}"
    const val hyperionAttr = "com.willowtreeapps.hyperion:hyperion-attr:${Versions.hyperion}"
    const val hyperionBuildConfig = "com.willowtreeapps.hyperion:hyperion-build-config:${Versions.hyperion}"
    const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:${Versions.hyperion}"
    const val hyperionDisk = "com.willowtreeapps.hyperion:hyperion-disk:${Versions.hyperion}"
    const val hyperionGeigerCounter =
        "com.willowtreeapps.hyperion:hyperion-geiger-counter:${Versions.hyperion}"
    const val hyperionMeasurement = "com.willowtreeapps.hyperion:hyperion-measurement:${Versions.hyperion}"
    const val hyperionPhoenix = "com.willowtreeapps.hyperion:hyperion-phoenix:${Versions.hyperion}"
    const val hyperionRecorder = "com.willowtreeapps.hyperion:hyperion-recorder:${Versions.hyperion}"
    const val hyperionSharedPreferences =
        "com.willowtreeapps.hyperion:hyperion-shared-preferences:${Versions.hyperion}"
    const val hyperionTimber = "com.willowtreeapps.hyperion:hyperion-timber:${Versions.hyperion}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val apollo = "com.apollographql.apollo:apollo-runtime:$apolloVersion"
    const val apolloCoroutinesSupport = "com.apollographql.apollo:apollo-coroutines-support:$apolloVersion"
    const val apolloAndroidSupport = "com.apollographql.apollo:apollo-android-support:$apolloVersion"

    const val junit = "junit:junit:${Versions.junit}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val archTest = "androidx.arch.core:core-testing:${Versions.archTest}"

    fun DependencyHandler.addCommon() {
        implementation(kotlin)
        implementation(coroutines)
        testImplementation(coroutinesTest)

        implementation(coreKtx)

        implementation(dagger)
        kapt(daggerCompiler)

        implementation(timber)

        implementation(jodaTime)

        testImplementation(junit)
        testImplementation(mockitoKotlin)
    }

    fun DependencyHandler.addHyperion() {
        debugImplementation(hyperionCore)
        debugImplementation(hyperionAttr)
        debugImplementation(hyperionBuildConfig)
        debugImplementation(hyperionCrash)
        debugImplementation(hyperionDisk)
        debugImplementation(hyperionGeigerCounter)
        debugImplementation(hyperionMeasurement)
        debugImplementation(hyperionPhoenix)
        debugImplementation(hyperionRecorder)
        debugImplementation(hyperionSharedPreferences)
        debugImplementation(hyperionTimber)
    }

    fun DependencyHandler.addRoom() {
        implementation(roomRuntime)
        kapt(roomCompiler)
        implementation(roomKtx)
    }

    fun DependencyHandler.apiFastAdapter() {
        api(fastAdapter)
        api(fastadapterDiff)
        api(fastadapterExpandable)
        api(fastadapterDrag)
        api(fastadapterPaged)
        api(fastadapterScroll)
        api(fastadapterSwipe)
        api(fastadapterUtils)
        api(fastadapterUI)
    }

    fun DependencyHandler.apiApollo() {
        api(apollo)
        api(apolloCoroutinesSupport)
        api(apolloAndroidSupport)
    }

    private fun DependencyHandler.api(dep: Any) {
        add("api", dep)
    }

    private fun DependencyHandler.implementation(dep: Any) {
        add("implementation", dep)
    }

    private fun DependencyHandler.debugImplementation(dep: Any) {
        add("debugImplementation", dep)
    }

    private fun DependencyHandler.releaseImplementation(dep: Any) {
        add("releaseImplementation", dep)
    }

    private fun DependencyHandler.testImplementation(dep: Any) {
        add("testImplementation", dep)
    }

    private fun DependencyHandler.kapt(dep: Any) {
        add("kapt", dep)
    }

    private fun DependencyHandler.compileOnly(dep: Any) {
        add("compileOnly", dep)
    }
}
