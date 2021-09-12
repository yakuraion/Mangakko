package net.yakuraion.mangakko.core_repositories

import kotlinx.coroutines.flow.Flow
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.ThemeMode

interface AppSettingsRepository {

    var themeMode: ThemeMode

    val themeModeFlow: Flow<ThemeMode>

    var mediaTypesToShow: MediaTypesToShow

    val mediaTypesToShowFlow: Flow<MediaTypesToShow>
}
