package net.yakuraion.mangakko.core_persistence_impl

import android.content.SharedPreferences
import com.google.gson.Gson
import net.yakuraion.mangakko.core_persistence.PreferencesManager
import javax.inject.Inject

class PreferencesManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PreferencesManager
