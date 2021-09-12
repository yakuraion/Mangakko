package net.yakuraion.mangakko.media_impl.ui.media_overview

import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.RATE_DESC
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaStatus.RELEASING
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_entity.MediaType.ANIME
import net.yakuraion.mangakko.core_entity.MediaType.MANGA

enum class MediaOverviewCategory {
    ONGOING_ANIME {

        override val mediaType: MediaType = ANIME

        override val sortTypes: List<MediaSortType> = listOf(POPULARITY_DESC)

        override val status: MediaStatus = RELEASING
    },

    MOST_POPULAR_ANIME {

        override val mediaType: MediaType = ANIME

        override val sortTypes: List<MediaSortType> = listOf(POPULARITY_DESC)
    },
    MOST_RATED_ANIME {

        override val mediaType: MediaType = ANIME

        override val sortTypes: List<MediaSortType> = listOf(RATE_DESC)
    },
    ONGOING_MANGA {

        override val mediaType: MediaType = MANGA

        override val sortTypes: List<MediaSortType> = listOf(POPULARITY_DESC)

        override val status: MediaStatus = RELEASING
    },

    MOST_POPULAR_MANGA {

        override val mediaType: MediaType = MANGA

        override val sortTypes: List<MediaSortType> = listOf(POPULARITY_DESC)
    },
    MOST_RATED_MANGA {

        override val mediaType: MediaType = MANGA

        override val sortTypes: List<MediaSortType> = listOf(RATE_DESC)
    };

    abstract val mediaType: MediaType

    abstract val sortTypes: List<MediaSortType>

    open val status: MediaStatus? = null
}
