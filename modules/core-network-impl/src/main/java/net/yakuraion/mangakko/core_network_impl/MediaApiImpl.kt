package net.yakuraion.mangakko.core_network_impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import net.yakuraion.mangakko.core_network.MediaApi
import net.yakuraion.mangakko.core_network.QueryPageMediaQuery
import net.yakuraion.mangakko.core_network.type.MediaSort
import javax.inject.Inject

class MediaApiImpl @Inject constructor(private val client: ApolloClient) : MediaApi {

    override suspend fun getMedia(
        page: Int,
        perPage: Int,
        sort: List<MediaSort>
    ): QueryPageMediaQuery.Page {
        val query = QueryPageMediaQuery(page, perPage, sort)
        return client.query(query).await().data?.Page()!!
    }
}
