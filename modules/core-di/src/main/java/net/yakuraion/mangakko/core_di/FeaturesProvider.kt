package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.MainFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature
}
