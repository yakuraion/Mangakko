package net.yakuraion.mangakko.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.favorites_impl.FavoritesFeatureImpl
import net.yakuraion.mangakko.main.MainFeature
import net.yakuraion.mangakko.main_impl.MainFeatureImpl
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.MediaFeatureImpl
import net.yakuraion.mangakko.pager.PagerFeature
import net.yakuraion.mangakko.pager_impl.PagerFeatureImpl

@Module
interface FeaturesModule {

    @Binds
    fun bindsMain(impl: MainFeatureImpl): MainFeature

    @Binds
    fun bindsPager(impl: PagerFeatureImpl): PagerFeature

    @Binds
    fun bindsMedia(impl: MediaFeatureImpl): MediaFeature

    @Binds
    fun bindsFavorites(impl: FavoritesFeatureImpl): FavoritesFeature
}
