package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_persistence.PreferencesManager

interface PersistenceProvider {

    fun providePreferencesManager(): PreferencesManager
}
