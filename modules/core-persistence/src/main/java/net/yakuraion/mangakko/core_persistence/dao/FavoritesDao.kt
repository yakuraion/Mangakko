package net.yakuraion.mangakko.core_persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.yakuraion.mangakko.core_persistence.entity.FavoriteMediaEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM FavoriteMediaEntity ORDER BY addedToFavoritesAt DESC")
    fun getAllFlow(): Flow<List<FavoriteMediaEntity>>

    @Query("SELECT * FROM FavoriteMediaEntity WHERE id = :mediaId")
    suspend fun getById(mediaId: Int): FavoriteMediaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteMediaEntity)

    @Query("DELETE FROM FavoriteMediaEntity WHERE id = :mediaId")
    suspend fun delete(mediaId: Int)
}
