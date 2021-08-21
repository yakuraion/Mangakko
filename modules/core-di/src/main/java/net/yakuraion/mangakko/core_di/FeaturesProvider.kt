package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.MainFeature
import net.yakuraion.mangakko.genres.GenresFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature

    fun provideGenresFeature(): GenresFeature
}
