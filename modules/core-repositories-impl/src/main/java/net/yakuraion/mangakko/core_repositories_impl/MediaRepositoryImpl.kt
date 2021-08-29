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

    override suspend fun getPageMedia(page: Int, perPage: Int, sortTypes: List<MediaSortType>): Page<Media> {
        val sortTypeApiList = sortTypes.map { MediaSortTypeToMediaSortApiMapper.invoke(it) }
        val response = mediaApi.getMedia(page, perPage, sortTypeApiList)
        return PageMediaApiToPageMediaMapper.invoke(response)
    }
}
