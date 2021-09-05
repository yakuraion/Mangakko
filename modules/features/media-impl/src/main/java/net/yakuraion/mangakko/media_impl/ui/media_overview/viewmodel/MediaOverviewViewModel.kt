package net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.media_impl.domain.MediaInteractor

class MediaOverviewViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaInteractor: MediaInteractor
) : BaseViewModel() {

    val inTrendMediaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getInTrendMedia(ITEMS_IN_CATEGORY))
    }

    val mostPopularMediaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostPopularMedia(ITEMS_IN_CATEGORY))
    }

    val mostRatedMediaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostRatedMedia(ITEMS_IN_CATEGORY))
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaOverviewViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaOverviewViewModel
    }

    companion object {

        private const val ITEMS_IN_CATEGORY = 10
    }
}
