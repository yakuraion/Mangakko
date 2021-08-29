package net.yakuraion.mangakko.pager_impl.ui.pager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_feature.ui.livedata.SingleLiveEvent

class PagerViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val openFirstPageLiveData: LiveData<Unit> = SingleLiveEvent<Unit>().apply { call() }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<PagerViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): PagerViewModel
    }
}
