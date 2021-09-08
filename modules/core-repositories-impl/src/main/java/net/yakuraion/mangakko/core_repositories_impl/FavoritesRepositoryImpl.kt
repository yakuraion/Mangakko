package net.yakuraion.mangakko.core_repositories_impl

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor() : FavoritesRepository {

    override suspend fun getFavoriteMediaList(): List<Media> {
        // todo realize
        return emptyList()
    }

    override suspend fun getIsFavoriteMedia(mediaId: Int): Boolean {
        // todo realize
        return false
    }

    override suspend fun setIsFavoriteMedia(mediaId: Int, isFavorite: Boolean) {
        // todo realize
        // empty
    }
}
