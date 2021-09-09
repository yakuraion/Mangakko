package net.yakuraion.mangakko.core_persistence_impl.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.yakuraion.mangakko.core_persistence.dao.FavoritesDao
import net.yakuraion.mangakko.core_persistence_impl.AppDatabase
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val APP_DATABASE_NAME = "APP_DATABASE"

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideFavoritesDao(db: AppDatabase): FavoritesDao = db.favoritesDao()
}
