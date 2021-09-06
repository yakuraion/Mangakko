package net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_ui.content.ContentState
import net.yakuraion.mangakko.core_ui.content.ContentState.CONTENT
import net.yakuraion.mangakko.core_ui.content.ContentState.PROGRESS

class MediaDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaRepository: MediaRepository
) : BaseViewModel() {

    private val mediaId: Int = savedStateHandle.get(ARG_MEDIA_ID)!!

    private val initMedia: Media? = savedStateHandle.get(ARG_MEDIA)

    private val mediaDetailsLiveData: LiveData<MediaDetails> = liveData(coroutineContext) {
        val media = withContext(dispatchers.io) {
            mediaRepository.getMediaDetails(mediaId)
        }
        emit(media)
    }

    val contentStateLiveData: LiveData<ContentState> = MediatorLiveData<ContentState>().apply {
        value = PROGRESS
        addSource(mediaDetailsLiveData) { value = CONTENT }
    }

    val mainColorLiveData: LiveData<Int> = MediatorLiveData<Int>().apply {
        initMedia?.let { value = it.mainColor }
        addSource(mediaDetailsLiveData) { mediaDetails -> mediaDetails.mainColor?.let { value = it } }
    }

    val titleLiveData: LiveData<String> = MediatorLiveData<String>().apply {
        initMedia?.let { value = it.title }
        addSource(mediaDetailsLiveData) { value = it.title }
    }

    val coverImageUrlLiveData: LiveData<String> = MediatorLiveData<String>().apply {
        initMedia?.let { value = it.imageUrl }
        addSource(mediaDetailsLiveData) { value = it.imageUrl }
    }

    val descriptionLiveData: LiveData<String> = Transformations.map(mediaDetailsLiveData) { it.description }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaDetailsViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaDetailsViewModel
    }

    companion object {

        const val ARG_MEDIA_ID = "MEDIA_ID"
        const val ARG_MEDIA = "MEDIA"
    }
}
