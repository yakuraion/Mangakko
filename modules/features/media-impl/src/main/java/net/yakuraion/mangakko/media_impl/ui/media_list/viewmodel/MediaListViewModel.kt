package net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel

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
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.DataSourcesFactoriesFactory

class MediaListViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val dataSourcesFactoriesFactory: DataSourcesFactoriesFactory
) : BaseViewModel() {

    private val sortTypes: List<MediaSortType> = savedStateHandle.get(ARG_SORT_TYPES)!!

    private val status: MediaStatus? = savedStateHandle.get(ARG_STATUS)

    private val dataSourceFactory: DataSource.Factory<Int, Media> = dataSourcesFactoriesFactory
        .createMediaDataSourceFactory(this, sortTypes, status)

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .build()

    val mediaPagedListLiveData: LiveData<PagedList<Media>> = LivePagedListBuilder(dataSourceFactory, config)
        .build()

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaListViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaListViewModel
    }

    companion object {

        const val ARG_SORT_TYPES = "SORT_TYPES"
        const val ARG_STATUS = "STATUS"

        private const val PAGE_SIZE = 40
    }
}
