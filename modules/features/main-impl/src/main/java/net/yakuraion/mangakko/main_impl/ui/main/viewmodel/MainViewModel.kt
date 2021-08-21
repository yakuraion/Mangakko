package net.yakuraion.mangakko.main_impl.ui.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_feature.ui.livedata.SingleLiveEvent

class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val showGenresFragmentLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

    init {
        showGenresFragmentLiveData.call()
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }
}
