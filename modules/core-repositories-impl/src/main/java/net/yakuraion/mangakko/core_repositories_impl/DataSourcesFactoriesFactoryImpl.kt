package net.yakuraion.mangakko.core_repositories_impl

import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory
import net.yakuraion.mangakko.core_repositories_impl.datasource.MediaDataSource
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataSourcesFactoriesFactoryImpl @Inject constructor(
    private val mediaDataSourceFactory: MediaDataSource.Factory
) : DataSourcesFactoriesFactory {

    override fun createMediaDataSourceFactory(
        coroutineContext: CoroutineContext,
        sortTypes: List<MediaSortType>,
        mediaType: MediaType?,
        status: MediaStatus?
    ): Factory<Int, Media> {
        return object : DataSource.Factory<Int, Media>() {
            override fun create(): DataSource<Int, Media> {
                return mediaDataSourceFactory.create(coroutineContext, sortTypes, mediaType, status)
            }
        }
    }
}
