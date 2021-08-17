package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.MainFeatureApi

interface FeaturesProvider {

    fun provideMainFeature(): MainFeatureApi
}
