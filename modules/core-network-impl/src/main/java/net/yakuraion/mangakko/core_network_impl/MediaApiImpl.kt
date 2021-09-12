package net.yakuraion.mangakko.core_network_impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import net.yakuraion.mangakko.core_network.MediaApi
import net.yakuraion.mangakko.core_network.QueryMediaDetailsQuery
import net.yakuraion.mangakko.core_network.QueryMediaDetailsQuery.Media
import net.yakuraion.mangakko.core_network.QueryPageMediaQuery
import net.yakuraion.mangakko.core_network.type.MediaSort
import net.yakuraion.mangakko.core_network.type.MediaStatus
import net.yakuraion.mangakko.core_network.type.MediaType
import javax.inject.Inject

class MediaApiImpl @Inject constructor(private val client: ApolloClient) : MediaApi {

    override suspend fun getMedia(
        page: Int,
        perPage: Int,
        sort: List<MediaSort>,
        type: MediaType?,
        status: MediaStatus?
    ): QueryPageMediaQuery.Page {
        val query = QueryPageMediaQuery(page, perPage, sort, Input.optional(type), Input.optional(status))
        return client.query(query).await().data?.Page()!!
    }

    override suspend fun getMediaDetails(id: Int): Media {
        val query = QueryMediaDetailsQuery(id)
        return client.query(query).await().data?.Media()!!
    }
}
