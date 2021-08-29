package net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel

class MediaOverviewViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers
) : BaseViewModel() {

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaOverviewViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaOverviewViewModel
    }
}
