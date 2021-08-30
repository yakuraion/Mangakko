package net.yakuraion.mangakko.core_repositories

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType

interface DataSourcesFactoriesFactory {

    fun createMediaDataSourceFactory(
        coroutineScope: CoroutineScope,
        sortTypes: List<MediaSortType>
    ): DataSource.Factory<Int, Media>
}
