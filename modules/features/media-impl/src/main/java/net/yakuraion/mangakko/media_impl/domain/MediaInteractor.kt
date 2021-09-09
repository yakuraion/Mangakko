package net.yakuraion.mangakko.media_impl.domain

import kotlinx.coroutines.withContext
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.RATE_DESC
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_repositories.MediaRepository
import javax.inject.Inject

class MediaInteractor @Inject constructor(
    private val dispatchers: Dispatchers,
    private val mediaRepository: MediaRepository
) {

    suspend fun getOngoingMedia(count: Int): List<Media> {
        return withContext(dispatchers.io) {
            mediaRepository.getPageMedia(
                0,
                count,
                listOf(POPULARITY_DESC),
                MediaStatus.RELEASING
            ).values
        }
    }

    suspend fun getMostPopularMedia(count: Int): List<Media> {
        return withContext(dispatchers.io) {
            mediaRepository.getPageMedia(0, count, listOf(POPULARITY_DESC)).values
        }
    }

    suspend fun getMostRatedMedia(count: Int): List<Media> {
        return withContext(dispatchers.io) {
            mediaRepository.getPageMedia(0, count, listOf(RATE_DESC)).values
        }
    }
}
