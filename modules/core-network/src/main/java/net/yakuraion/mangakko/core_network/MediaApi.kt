package net.yakuraion.mangakko.core_network

import net.yakuraion.mangakko.core_network.type.MediaSort

interface MediaApi {

    suspend fun getMedia(page: Int, perPage: Int, genre: String, sort: MediaSort): QueryPageMediaQuery.Page
}
