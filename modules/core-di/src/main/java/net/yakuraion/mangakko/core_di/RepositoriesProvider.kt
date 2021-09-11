package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_repositories.MediaRepository

interface RepositoriesProvider {

    fun provideDataSourcesFactoriesFactory(): DataSourcesFactoriesFactory

    fun provideMediaRepository(): MediaRepository

    fun provideFavoritesRepository(): FavoritesRepository
}
