package net.yakuraion.mangakko.core_repositories_impl.datasource

import androidx.paging.PageKeyedDataSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.runBlocking
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_repositories.MediaRepository
import kotlin.coroutines.CoroutineContext

class MediaDataSource @AssistedInject constructor(
    @Assisted private val coroutineContext: CoroutineContext,
    @Assisted private val sortTypes: List<MediaSortType>,
    @Assisted private val mediaType: MediaType?,
    @Assisted private val status: MediaStatus?,
    private val mediaRepository: MediaRepository
) : PageKeyedDataSource<Int, Media>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Media>) {
        runBlocking(coroutineContext) {
            val page = mediaRepository.getPageMedia(
                0,
                params.requestedLoadSize,
                sortTypes,
                mediaType,
                status
            )
            val nextPageKey = if (page.hasNextPage) 1 else null
            if (params.placeholdersEnabled) {
                callback.onResult(page.values, 0, page.totalCount, null, nextPageKey)
            } else {
                callback.onResult(page.values, null, nextPageKey)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {
        // empty
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {
        runBlocking(coroutineContext) {
            val page = mediaRepository.getPageMedia(
                params.key,
                params.requestedLoadSize,
                sortTypes,
                mediaType,
                status
            )
            val nextPageKey = if (page.hasNextPage) params.key + 1 else null
            callback.onResult(page.values, nextPageKey)
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(
            coroutineContext: CoroutineContext,
            sortTypes: List<MediaSortType>,
            mediaType: MediaType?,
            status: MediaStatus?
        ): MediaDataSource
    }
}
