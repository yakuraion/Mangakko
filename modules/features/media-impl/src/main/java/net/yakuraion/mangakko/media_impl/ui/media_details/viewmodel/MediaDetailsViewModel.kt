package net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel

class MediaDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers
) : BaseViewModel() {

    val mediaId: Int = savedStateHandle.get(ARG_MEDIA_ID)!!

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaDetailsViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaDetailsViewModel
    }

    companion object {

        const val ARG_MEDIA_ID = "MEDIA_ID"
    }
}
