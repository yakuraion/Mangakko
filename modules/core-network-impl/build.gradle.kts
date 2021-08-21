import Libraries.addCommon
import Libraries.implementationApollo

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.apollographql.apollo") version apolloVersion
}

dependencies {
    addCommon()
    implementationApollo()
    api(project(":modules:core-network"))
    implementation(project(":modules:core-di"))
    implementation(project(":modules:core-persistence"))
}
