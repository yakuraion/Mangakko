package net.yakuraion.mangakko.core_persistence

import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.ThemeMode

interface PreferencesManager {

    var themeMode: ThemeMode

    var mediaTypesToShow: MediaTypesToShow
}
