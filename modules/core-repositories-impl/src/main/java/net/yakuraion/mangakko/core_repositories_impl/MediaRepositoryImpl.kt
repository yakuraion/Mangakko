package net.yakuraion.mangakko.core_repositories_impl

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.Page
import net.yakuraion.mangakko.core_network.MediaApi
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaSortTypeToMediaSortApiMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.PageMediaApiToPageMediaMapper
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaApi: MediaApi
) : MediaRepository {

    override suspend fun getPageMedia(
        page: Int,
        perPage: Int,
        genre: String,
        sortType: MediaSortType
    ): Page<Media> {
        val sortTypeApi = MediaSortTypeToMediaSortApiMapper.invoke(sortType)
        val response = mediaApi.getMedia(page, perPage, genre, sortTypeApi)
        return PageMediaApiToPageMediaMapper.invoke(response)
    }
}
