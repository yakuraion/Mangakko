package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_repositories.AppSettingsRepository
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_repositories.MediaRepository

interface RepositoriesProvider {

    fun provideDataSourcesFactoriesFactory(): DataSourcesFactoriesFactory

    fun provideAppSettingsRepository(): AppSettingsRepository

    fun provideMediaRepository(): MediaRepository

    fun provideFavoritesRepository(): FavoritesRepository
}
