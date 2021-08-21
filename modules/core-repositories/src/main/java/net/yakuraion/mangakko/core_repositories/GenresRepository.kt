package net.yakuraion.mangakko.core_repositories

interface GenresRepository {

    suspend fun getGenres(): List<String>
}
