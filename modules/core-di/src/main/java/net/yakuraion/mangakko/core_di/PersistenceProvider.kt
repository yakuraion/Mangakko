package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_persistence.PreferencesManager
import net.yakuraion.mangakko.core_persistence.dao.FavoritesDao

interface PersistenceProvider {

    fun providePreferencesManager(): PreferencesManager

    fun provideFavoritesDao(): FavoritesDao
}
