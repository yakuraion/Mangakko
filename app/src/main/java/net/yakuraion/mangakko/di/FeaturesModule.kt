package net.yakuraion.mangakko.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.MainFeature
import net.yakuraion.mangakko.genres.GenresFeature
import net.yakuraion.mangakko.genres_impl.GenresFeatureImpl
import net.yakuraion.mangakko.main_impl.MainFeatureImpl
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.MediaFeatureImpl

@Module
interface FeaturesModule {

    @Binds
    fun bindsMain(impl: MainFeatureImpl): MainFeature

    @Binds
    fun bindsGenres(impl: GenresFeatureImpl): GenresFeature

    @Binds
    fun bindsMedia(impl: MediaFeatureImpl): MediaFeature
}
