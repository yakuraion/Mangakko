package net.yakuraion.mangakko.core_network_impl.di.modules

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.core_network.MediaApi
import net.yakuraion.mangakko.core_network_impl.MediaApiImpl
import javax.inject.Singleton

@Module
interface ApiListModule {

    @Binds
    @Singleton
    fun bindsMedia(impl: MediaApiImpl): MediaApi
}
