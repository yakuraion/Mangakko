package net.yakuraion.mangakko.core_repositories_impl.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories.GenresRepository
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_repositories_impl.DataSourcesFactoriesFactoryImpl
import net.yakuraion.mangakko.core_repositories_impl.GenresRepositoryImpl
import net.yakuraion.mangakko.core_repositories_impl.MediaRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindsDataSourcesFactoriesFactory(impl: DataSourcesFactoriesFactoryImpl): DataSourcesFactoriesFactory

    @Binds
    @Singleton
    fun bindsGenres(impl: GenresRepositoryImpl): GenresRepository

    @Binds
    @Singleton
    fun bindsMedia(impl: MediaRepositoryImpl): MediaRepository
}
