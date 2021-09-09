package net.yakuraion.mangakko.favorites_impl.ui.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_feature.ui.livedata.SingleLiveEvent

class FavoritesViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers
) : BaseViewModel() {

    val showFavoritesListFragmentLiveData: LiveData<Unit> = SingleLiveEvent<Unit>().apply { call() }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<FavoritesViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): FavoritesViewModel
    }
}
