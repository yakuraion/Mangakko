package net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaType.ANIME
import net.yakuraion.mangakko.core_entity.MediaType.MANGA
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.media_impl.domain.MediaInteractor

class MediaOverviewViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaInteractor: MediaInteractor
) : BaseViewModel() {

    val ongoingAnimeListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getOngoingMedia(ITEMS_IN_CATEGORY, ANIME))
    }

    val mostPopularAnimeListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostPopularMedia(ITEMS_IN_CATEGORY, ANIME))
    }

    val mostRatedAnimeListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostRatedMedia(ITEMS_IN_CATEGORY, ANIME))
    }

    val ongoingMangaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getOngoingMedia(ITEMS_IN_CATEGORY, MANGA))
    }

    val mostPopularMangaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostPopularMedia(ITEMS_IN_CATEGORY, MANGA))
    }

    val mostRatedMangaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(ITEMS_IN_CATEGORY) { null })
        emit(mediaInteractor.getMostRatedMedia(ITEMS_IN_CATEGORY, MANGA))
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaOverviewViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaOverviewViewModel
    }

    companion object {

        private const val ITEMS_IN_CATEGORY = 10
    }
}
