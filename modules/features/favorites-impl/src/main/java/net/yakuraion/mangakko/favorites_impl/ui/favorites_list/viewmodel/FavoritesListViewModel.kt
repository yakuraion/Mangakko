package net.yakuraion.mangakko.favorites_impl.ui.favorites_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.MediaRepository

class FavoritesListViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaRepository: MediaRepository
) : BaseViewModel() {

    val mediaListLiveData: LiveData<List<Media?>> = liveData(coroutineContext) {
        emit(List(4) { null })
        withContext(dispatchers.io) {
            val mediaList = mediaRepository.getPageMedia(0, 20, listOf(POPULARITY_DESC)).values
            emit(mediaList)
        }
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<FavoritesListViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): FavoritesListViewModel
    }
}
