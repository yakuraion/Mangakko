package net.yakuraion.mangakko.core_repositories_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_persistence.dao.FavoritesDao
import net.yakuraion.mangakko.core_repositories.FavoritesRepository
import net.yakuraion.mangakko.core_repositories_impl.mappers.FavoriteMediaEntityToMediaMapper
import net.yakuraion.mangakko.core_repositories_impl.mappers.MediaToFavoriteMediaEntityMapper
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {

    override fun getFavoriteMediaListFlow(): Flow<List<Media>> {
        return favoritesDao.getAllFlow().map { list ->
            list.map { FavoriteMediaEntityToMediaMapper.invoke(it) }
        }
    }

    override suspend fun getIsFavoriteMedia(mediaId: Int): Boolean {
        return favoritesDao.getById(mediaId) != null
    }

    override suspend fun addToFavorite(media: Media) {
        val entity = MediaToFavoriteMediaEntityMapper.invoke(media, System.currentTimeMillis())
        favoritesDao.insert(entity)
    }

    override suspend fun removeFromFavorite(mediaId: Int) {
        favoritesDao.delete(mediaId)
    }
}
