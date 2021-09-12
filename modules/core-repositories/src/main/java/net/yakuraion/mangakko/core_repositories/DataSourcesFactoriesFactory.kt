package net.yakuraion.mangakko.core_repositories

import androidx.paging.DataSource
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import kotlin.coroutines.CoroutineContext

interface DataSourcesFactoriesFactory {

    fun createMediaDataSourceFactory(
        coroutineContext: CoroutineContext,
        sortTypes: List<MediaSortType>,
        mediaType: MediaType?,
        status: MediaStatus?
    ): DataSource.Factory<Int, Media>
}
