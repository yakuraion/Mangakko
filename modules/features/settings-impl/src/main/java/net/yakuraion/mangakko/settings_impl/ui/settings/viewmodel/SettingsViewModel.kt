package net.yakuraion.mangakko.settings_impl.ui.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.ThemeMode
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseViewModel
import net.yakuraion.mangakko.core_feature.ui.livedata.SingleLiveEvent
import net.yakuraion.mangakko.core_repositories.AppSettingsRepository

class SettingsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val dispatchers: Dispatchers,
    private val appSettingsRepository: AppSettingsRepository
) : BaseViewModel() {

    val setThemeModeLiveData: LiveData<ThemeMode> = SingleLiveEvent<ThemeMode>().apply {
        value = appSettingsRepository.themeMode
    }

    val setMediaTypesLiveData: LiveData<MediaTypesToShow> = SingleLiveEvent<MediaTypesToShow>().apply {
        value = appSettingsRepository.mediaTypesToShow
    }

    fun onThemeSystemChecked() {
        appSettingsRepository.themeMode = ThemeMode.SYSTEM
    }

    fun onThemeLightChecked() {
        appSettingsRepository.themeMode = ThemeMode.LIGHT
    }

    fun onThemeDarkChecked() {
        appSettingsRepository.themeMode = ThemeMode.DARK
    }

    fun onMediaTypesAnimeAndMangaChecked() {
        appSettingsRepository.mediaTypesToShow = MediaTypesToShow.ANIME_AND_MANGA
    }

    fun onMediaTypesAnimeChecked() {
        appSettingsRepository.mediaTypesToShow = MediaTypesToShow.ONLY_ANIME
    }

    fun onMediaTypesMangaChecked() {
        appSettingsRepository.mediaTypesToShow = MediaTypesToShow.ONLY_MANGA
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<SettingsViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): SettingsViewModel
    }
}
