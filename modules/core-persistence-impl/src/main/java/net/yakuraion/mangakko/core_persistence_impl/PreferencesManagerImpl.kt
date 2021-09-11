package net.yakuraion.mangakko.core_persistence_impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
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

    companion object {

        private const val THEME_MODE_KEY = "THEME_MODE"
    }
}
