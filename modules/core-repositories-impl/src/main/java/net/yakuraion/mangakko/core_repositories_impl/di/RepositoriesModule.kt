package net.yakuraion.mangakko.core_repositories_impl.di

import dagger.Binds
import dagger.Module
import net.yakuraion.mangakko.core_repositories.GenresRepository
import net.yakuraion.mangakko.core_repositories_impl.GenresRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindsGenres(impl: GenresRepositoryImpl): GenresRepository
}
