package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.main.MainFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature
}
