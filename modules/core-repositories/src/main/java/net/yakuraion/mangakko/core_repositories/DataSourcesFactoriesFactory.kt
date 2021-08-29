package net.yakuraion.mangakko.core_repositories

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import net.yakuraion.mangakko.core_entity.Media

interface DataSourcesFactoriesFactory {

    fun createMediaDataSourceFactory(
        coroutineScope: CoroutineScope
    ): DataSource.Factory<Int, Media>
}
