package net.yakuraion.mangakko.core_persistence_impl.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.yakuraion.mangakko.core_persistence.PreferencesManager
import net.yakuraion.mangakko.core_persistence_impl.PreferencesManagerImpl
import javax.inject.Singleton

@Module
interface PersistenceModule {

    @Binds
    @Singleton
    fun bindsPreferenceManager(impl: PreferencesManagerImpl): PreferencesManager

    @Module
    companion object {

        private const val PREFERENCES_NAME = "preferences"

        @JvmStatic
        @Singleton
        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        }
    }
}
