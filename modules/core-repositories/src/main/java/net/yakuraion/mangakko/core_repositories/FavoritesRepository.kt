package net.yakuraion.mangakko.core_repositories

import kotlinx.coroutines.flow.Flow
import net.yakuraion.mangakko.core_entity.Media

interface FavoritesRepository {

    fun getFavoriteMediaListFlow(): Flow<List<Media>>

    suspend fun getIsFavoriteMedia(mediaId: Int): Boolean

    suspend fun addToFavorite(media: Media)

    suspend fun removeFromFavorite(mediaId: Int)
}
