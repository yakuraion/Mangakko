package net.yakuraion.mangakko.core_repositories_impl

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories_impl.datasource.MediaDataSource
import javax.inject.Inject

class DataSourcesFactoriesFactoryImpl @Inject constructor(
    private val mediaDataSourceFactory: MediaDataSource.Factory
) : DataSourcesFactoriesFactory {

    override fun createMediaDataSourceFactory(
        coroutineScope: CoroutineScope
    ): DataSource.Factory<Int, Media> {
        return object : DataSource.Factory<Int, Media>() {
            override fun create(): DataSource<Int, Media> {
                return mediaDataSourceFactory.create(coroutineScope)
            }
        }
    }
}
