package net.yakuraion.mangakko.genres_impl.ui.genres.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_repositories.GenresRepository

class GenresViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val genresRepository: GenresRepository
) : BaseViewModel() {

    val genresLiveData: LiveData<List<String>> = flow {
        emit(genresRepository.getGenres())
    }.flowOn(dispatchers.io).asLiveData(coroutineContext)

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<GenresViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): GenresViewModel
    }
}
