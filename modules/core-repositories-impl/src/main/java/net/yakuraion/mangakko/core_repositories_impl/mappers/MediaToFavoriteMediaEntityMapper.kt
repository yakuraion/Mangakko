package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_persistence.entity.FavoriteMediaEntity

object MediaToFavoriteMediaEntityMapper {

    fun invoke(value: Media, addedToFavoritesAt: Long): FavoriteMediaEntity {
        return FavoriteMediaEntity(
            addedToFavoritesAt = addedToFavoritesAt,
            id = value.id,
            title = value.title,
            imageUrl = value.imageUrl,
            mainColor = value.mainColor,
            score = value.score
        )
    }
}
