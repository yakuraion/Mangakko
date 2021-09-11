package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.MainFeature
import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager.PagerFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature

    fun providePagerFeature(): PagerFeature

    fun provideMediaFeature(): MediaFeature

    fun provideFavoritesFeature(): FavoritesFeature
}
