package net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_feature.ui.livedata.combineLatestLiveData
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_repositories.MediaRepository
import net.yakuraion.mangakko.core_uikit.content.ContentState
import net.yakuraion.mangakko.core_uikit.content.ContentState.CONTENT
import net.yakuraion.mangakko.core_uikit.content.ContentState.PROGRESS

class MediaDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val mediaRepository: MediaRepository,
    private val favoritesRepository: FavoritesRepository
) : BaseViewModel() {

    private val mediaId: Int = savedStateHandle.get(ARG_MEDIA_ID)!!

    private val initMedia: Media? = savedStateHandle.get(ARG_MEDIA)

    private val mediaDetailsLiveData: LiveData<MediaDetails> = liveData(coroutineContext) {
        val media = withContext(dispatchers.io) {
            mediaRepository.getMediaDetails(mediaId)
        }
        emit(media)
    }

    private val isFavoriteLiveData: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        val repositoryLiveData = liveData(coroutineContext) {
            emit(favoritesRepository.getIsFavoriteMedia(mediaId))
        }
        addSource(repositoryLiveData) { value = it }
    }

    val contentStateLiveData: LiveData<ContentState> = MediatorLiveData<ContentState>().apply {
        value = if (initMedia != null) CONTENT else PROGRESS
        addSource(mediaDetailsLiveData) { value = CONTENT }
    }

    val mainColorLiveData: LiveData<Int> = MediatorLiveData<Int>().apply {
        initMedia?.mainColor?.let { value = it }
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

    val isShowingPlaceholderLiveData: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        value = true
        addSource(mediaDetailsLiveData) { value = false }
    }

    val isShowingScoreWithLikeLiveData: LiveData<Boolean> =
        Transformations.map(isShowingPlaceholderLiveData) { !it }

    val scoreWithIsFavoriteLiveData: LiveData<ScoreWithIsFavoriteParams> = combineLatestLiveData(
        mediaDetailsLiveData,
        isFavoriteLiveData
    ) { media, isFavorite ->
        ScoreWithIsFavoriteParams(media.score, media.rateRank, media.popularityRank, isFavorite)
    }

    val descriptionLiveData: LiveData<String> = Transformations.map(mediaDetailsLiveData) { it.description }

    fun onLikeClick() {
        val newIsFavorite = !(isFavoriteLiveData.value ?: false)
        isFavoriteLiveData.value = newIsFavorite
        launch {
            withContext(dispatchers.io) {
                favoritesRepository.setIsFavoriteMedia(mediaId, newIsFavorite)
            }
        }
    }

    class ScoreWithIsFavoriteParams(
        val score: Int?,
        val rateRank: Int?,
        val popularityRank: Int?,
        val isFavorite: Boolean
    )

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MediaDetailsViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): MediaDetailsViewModel
    }

    companion object {

        const val ARG_MEDIA_ID = "MEDIA_ID"
        const val ARG_MEDIA = "MEDIA"
    }
}
