package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_repositories.GenresRepository

interface RepositoriesProvider {

    fun provideGenresRepository(): GenresRepository
}
