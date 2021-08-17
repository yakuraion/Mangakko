package net.yakuraion.mangakko.main_impl.ui.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel

class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }
}
