import Libraries.addCommon
import Libraries.apiApollo

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.apollographql.apollo") version apolloVersion
}

dependencies {
    addCommon()
    implementation(project(":modules:core-utils"))
    apiApollo()
}
