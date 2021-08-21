package net.yakuraion.mangakko.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.MainFeature
import net.yakuraion.mangakko.main_impl.MainFeatureImpl

@Module
interface FeaturesModule {

    @Binds
    fun bindsMain(impl: MainFeatureImpl): MainFeature
}
