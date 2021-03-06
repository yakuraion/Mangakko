package net.yakuraion.mangakko.core_repositories_impl

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_entity.Page
import net.yakuraion.mangakko.core_network.MediaApi
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaDetailsApiToMediaDetailsMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaSortTypeToMediaSortApiMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaStatusToMediaStatusApiMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaTypeToMediaTypeApiMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.PageMediaApiToPageMediaMapper
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaApi: MediaApi
) : MediaRepository {

    override suspend fun getPageMedia(
        page: Int,
        perPage: Int,
        sortTypes: List<MediaSortType>,
        mediaType: MediaType?,
        status: MediaStatus?
    ): Page<Media> {
        val sortTypeApiList = sortTypes.map { MediaSortTypeToMediaSortApiMapper.invoke(it) }
        val typeApi = mediaType?.let { MediaTypeToMediaTypeApiMapper.invoke(it) }
        val statusApi = status?.let { MediaStatusToMediaStatusApiMapper.invoke(it) }
        val response = mediaApi.getMedia(page, perPage, sortTypeApiList, typeApi, statusApi)
        return PageMediaApiToPageMediaMapper.invoke(response)
    }

    override suspend fun getMediaDetails(id: Int): MediaDetails {
        val response = mediaApi.getMediaDetails(id)
        return MediaDetailsApiToMediaDetailsMapper.invoke(response)
    }
}
