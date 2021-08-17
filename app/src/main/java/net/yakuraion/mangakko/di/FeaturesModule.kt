package net.yakuraion.mangakko.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.MainFeatureApi
import net.yakuraion.mangakko.main_impl.MainFeatureApiImpl

@Module
interface FeaturesModule {

    @Binds
    fun bindsMainFeatureApi(impl: MainFeatureApiImpl): MainFeatureApi
}
