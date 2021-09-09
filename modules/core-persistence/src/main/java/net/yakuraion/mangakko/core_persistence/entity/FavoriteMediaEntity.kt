package net.yakuraion.mangakko.core_persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteMediaEntity(
    val addedToFavoritesAt: Long,
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val mainColor: Int?,
    val score: Int?
)
