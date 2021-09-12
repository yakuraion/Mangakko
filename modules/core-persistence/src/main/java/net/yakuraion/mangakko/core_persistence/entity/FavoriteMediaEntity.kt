package net.yakuraion.mangakko.core_persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.yakuraion.mangakko.core_entity.MediaType

@Entity
class FavoriteMediaEntity(
    val addedToFavoritesAt: Long,
    @PrimaryKey val id: Int,
    val title: String,
    val type: MediaType,
    val imageUrl: String,
    val mainColor: Int?,
    val score: Int?
)
