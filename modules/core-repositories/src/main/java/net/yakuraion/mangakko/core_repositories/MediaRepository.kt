package net.yakuraion.mangakko.core_repositories

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_entity.Page

interface MediaRepository {

    suspend fun getPageMedia(
        page: Int,
        perPage: Int,
        sortTypes: List<MediaSortType>,
        mediaType: MediaType?,
        status: MediaStatus? = null
    ): Page<Media>

    suspend fun getMediaDetails(id: Int): MediaDetails
}
