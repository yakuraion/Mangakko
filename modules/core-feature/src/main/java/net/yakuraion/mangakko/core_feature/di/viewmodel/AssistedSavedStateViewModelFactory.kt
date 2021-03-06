package net.yakuraion.mangakko.core_feature.di.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Фабрика создания конкретной ViewModel
 */
interface AssistedSavedStateViewModelFactory<T : ViewModel> {

    fun create(savedStateHandle: SavedStateHandle): T
}
