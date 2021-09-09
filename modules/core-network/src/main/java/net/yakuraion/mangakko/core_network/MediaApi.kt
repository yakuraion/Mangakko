package net.yakuraion.mangakko.core_network

import net.yakuraion.mangakko.core_network.type.MediaSort
import net.yakuraion.mangakko.core_network.type.MediaStatus

interface MediaApi {

    suspend fun getMedia(
        page: Int,
        perPage: Int,
        sort: List<MediaSort>,
        status: MediaStatus?
    ): QueryPageMediaQuery.Page

    suspend fun getMediaDetails(id: Int): QueryMediaDetailsQuery.Media
}
