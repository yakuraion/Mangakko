package net.yakuraion.mangakko.core_persistence_impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.ThemeMode
import net.yakuraion.mangakko.core_persistence.PreferencesManager
import javax.inject.Inject

class PreferencesManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PreferencesManager {

    override var themeMode: ThemeMode
        get() = ThemeMode.values()[sharedPreferences.getInt(THEME_MODE_KEY, ThemeMode.SYSTEM.ordinal)]
        set(value) {
            sharedPreferences.edit(commit = true) { putInt(THEME_MODE_KEY, value.ordinal) }
        }

    override var mediaTypesToShow: MediaTypesToShow
        get(): MediaTypesToShow {
            val ordinal = sharedPreferences.getInt(
                MEDIA_TYPES_TO_SHOW_KEY,
                MediaTypesToShow.ANIME_AND_MANGA.ordinal
            )
            return MediaTypesToShow.values()[ordinal]
        }
        set(value) {
            sharedPreferences.edit { putInt(MEDIA_TYPES_TO_SHOW_KEY, value.ordinal) }
        }

    companion object {

        private const val THEME_MODE_KEY = "THEME_MODE"
        private const val MEDIA_TYPES_TO_SHOW_KEY = "MEDIA_TYPES_TO_SHOW"
    }
}
