package net.yakuraion.mangakko.media_impl.ui.media_overview

import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.RATE_DESC
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaStatus.RELEASING

enum class MediaOverviewCategory {
    ONGOING {

        override fun getSortTypes(): List<MediaSortType> = listOf(POPULARITY_DESC)

        override fun getStatus(): MediaStatus = RELEASING
    },

    MOST_POPULAR {

        override fun getSortTypes(): List<MediaSortType> = listOf(POPULARITY_DESC)
    },
    MOST_RATED {

        override fun getSortTypes(): List<MediaSortType> = listOf(RATE_DESC)
    };

    abstract fun getSortTypes(): List<MediaSortType>

    open fun getStatus(): MediaStatus? = null
}
