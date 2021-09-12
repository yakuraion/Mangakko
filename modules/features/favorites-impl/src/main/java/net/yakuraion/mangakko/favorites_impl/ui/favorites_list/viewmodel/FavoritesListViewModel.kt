package net.yakuraion.mangakko.favorites_impl.ui.favorites_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.flowOn
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_uikit.content.ContentState
import net.yakuraion.mangakko.core_uikit.content.ContentState.CONTENT
import net.yakuraion.mangakko.core_uikit.content.ContentState.EMPTY

class FavoritesListViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val favoritesRepository: FavoritesRepository
) : BaseViewModel() {

    val mediaListLiveData: LiveData<List<Media?>> = favoritesRepository.getFavoriteMediaListFlow()
        .flowOn(dispatchers.io)
        .asLiveData(coroutineContext)

    val contentStateLiveData: LiveData<ContentState> = Transformations.map(mediaListLiveData) { list ->
        if (list.isNotEmpty()) CONTENT else EMPTY
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<FavoritesListViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): FavoritesListViewModel
    }
}
