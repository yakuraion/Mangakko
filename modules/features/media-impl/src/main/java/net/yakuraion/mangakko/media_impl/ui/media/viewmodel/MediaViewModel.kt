package net.yakuraion.mangakko.media_impl.ui.media.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory

class MediaViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val dataSourcesFactoriesFactory: DataSourcesFactoriesFactory
) : BaseViewModel() {

    private val genre: String = savedStateHandle.get(ARG_GENRE)!!

    private val dataSourceFactory: DataSource.Factory<Int, Media> = dataSourcesFactoriesFactory
        .createMediaDataSourceFactory(this, genre)

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .build()

    val mediaPagedListLiveData: LiveData<PagedList<Media>> = LivePagedListBuilder(dataSourceFactory, config)
        .build()

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaViewModel
    }

    companion object {

        const val ARG_GENRE = "GENRE"

        private const val PAGE_SIZE = 40
    }
}

