package net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.MediaRepository

class MediaDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaRepository: MediaRepository
) : BaseViewModel() {

    private val mediaId: Int = savedStateHandle.get(ARG_MEDIA_ID)!!

    val mediaDetailsLiveData: LiveData<MediaDetails> = liveData(coroutineContext) {
        val media = withContext(dispatchers.io) {
            mediaRepository.getMediaDetails(mediaId)
        }
        emit(media)
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaDetailsViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaDetailsViewModel
    }

    companion object {

        const val ARG_MEDIA_ID = "MEDIA_ID"
    }
}
