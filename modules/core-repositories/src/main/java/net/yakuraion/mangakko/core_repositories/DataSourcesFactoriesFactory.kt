package net.yakuraion.mangakko.core_repositories

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType

interface DataSourcesFactoriesFactory {

    fun createMediaDataSourceFactory(
        coroutineScope: CoroutineScope,
        sortTypes: List<MediaSortType>,
        mediaType: MediaType?,
        status: MediaStatus?
    ): DataSource.Factory<Int, Media>
}
