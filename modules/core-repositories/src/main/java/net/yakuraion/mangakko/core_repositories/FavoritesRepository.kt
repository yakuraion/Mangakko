package net.yakuraion.mangakko.core_repositories

import net.yakuraion.mangakko.core_entity.Media

interface FavoritesRepository {

    suspend fun getFavoriteMediaList(): List<Media>

    suspend fun getIsFavoriteMedia(mediaId: Int): Boolean

    suspend fun setIsFavoriteMedia(mediaId: Int, isFavorite: Boolean)
}
