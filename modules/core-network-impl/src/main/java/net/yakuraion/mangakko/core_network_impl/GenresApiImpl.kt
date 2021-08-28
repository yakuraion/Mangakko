package net.yakuraion.mangakko.core_network_impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import net.yakuraion.mangakko.core_network.GenresApi
import javax.inject.Inject

class GenresApiImpl @Inject constructor(private val client: ApolloClient) : GenresApi {

    override suspend fun getGenres(): List<String> {
        return client.query(QueryGenreCollectionQuery()).await().data?.GenreCollection!!
    }
}
