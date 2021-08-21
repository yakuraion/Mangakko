package net.yakuraion.mangakko.core_repositories_impl

import net.yakuraion.mangakko.core_network.GenresApi
import net.yakuraion.mangakko.core_repositories.GenresRepository
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresApi: GenresApi
) : GenresRepository {

    override suspend fun getGenres(): List<String> {
        return genresApi.getGenres()
    }
}
