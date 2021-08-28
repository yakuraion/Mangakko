package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.MainFeature
import net.yakuraion.mangakko.genres.GenresFeature
import net.yakuraion.mangakko.media.MediaFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature

    fun provideGenresFeature(): GenresFeature

    fun provideMediaFeature(): MediaFeature
}
