package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.RATE_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.TRENDING_DESC
import net.yakuraion.mangakko.core_network.type.MediaSort

object MediaSortTypeToMediaSortApiMapper {

    fun invoke(value: MediaSortType): MediaSort {
        return when (value) {
            TRENDING_DESC -> MediaSort.TRENDING_DESC
            POPULARITY_DESC -> MediaSort.POPULARITY_DESC
            RATE_DESC -> MediaSort.SCORE_DESC
        }
    }
}
