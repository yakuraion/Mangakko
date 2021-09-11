package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.main.MainFeature
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager.PagerFeature
import net.yakuraion.mangakko.settings.SettingsFeature

interface FeaturesProvider {

    fun provideMainFeature(): MainFeature

    fun providePagerFeature(): PagerFeature

    fun provideMediaFeature(): MediaFeature

    fun provideFavoritesFeature(): FavoritesFeature

    fun provideSettingsFeature(): SettingsFeature
}
