package net.yakuraion.mangakko.core_persistence_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import net.yakuraion.mangakko.core_persistence.dao.FavoritesDao
import net.yakuraion.mangakko.core_persistence.entity.FavoriteMediaEntity

@Database(entities = [FavoriteMediaEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}
