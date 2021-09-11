package net.yakuraion.mangakko.core_repositories_impl.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.core_repositories.AppSettingsRepository
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_repositories_impl.AppSettingsRepositoryImpl
import net.yakuraion.mangakko.core_repositories_impl.DataSourcesFactoriesFactoryImpl
import net.yakuraion.mangakko.core_repositories_impl.FavoritesRepositoryImpl
import net.yakuraion.mangakko.core_repositories_impl.MediaRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindsDataSourcesFactoriesFactory(impl: DataSourcesFactoriesFactoryImpl): DataSourcesFactoriesFactory

    @Binds
    @Singleton
    fun bindsAppSettings(impl: AppSettingsRepositoryImpl): AppSettingsRepository

    @Binds
    @Singleton
    fun bindsMedia(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    @Singleton
    fun bindsFavorites(impl: FavoritesRepositoryImpl): FavoritesRepository
}
