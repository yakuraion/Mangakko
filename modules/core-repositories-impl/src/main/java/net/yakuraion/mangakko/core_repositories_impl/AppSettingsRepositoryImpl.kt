package net.yakuraion.mangakko.core_repositories_impl

import kotlinx.coroutines.flow.MutableStateFlow
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.ThemeMode
import net.yakuraion.mangakko.core_persistence.PreferencesManager
import net.yakuraion.mangakko.core_repositories.AppSettingsRepository
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : AppSettingsRepository {

    override var themeMode: ThemeMode
        get() = preferencesManager.themeMode
        set(value) {
            preferencesManager.themeMode = value
            themeModeFlow.tryEmit(value)
        }

    override val themeModeFlow: MutableStateFlow<ThemeMode> = MutableStateFlow(themeMode)

    override var mediaTypesToShow: MediaTypesToShow
        get() = preferencesManager.mediaTypesToShow
        set(value) {
            preferencesManager.mediaTypesToShow = value
            mediaTypesToShowFlow.tryEmit(value)
        }

    override val mediaTypesToShowFlow: MutableStateFlow<MediaTypesToShow> = MutableStateFlow(mediaTypesToShow)
}
