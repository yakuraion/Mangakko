package net.yakuraion.mangakko.core_network

interface GenresApi {

    suspend fun getGenres(): List<String>
}
