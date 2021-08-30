package net.yakuraion.mangakko.media_impl.ui.media_overview

import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaSortType.POPULARITY_DESC
import net.yakuraion.mangakko.core_entity.MediaSortType.RATE_DESC

enum class MediaOverviewCategory {
    MOST_POPULAR {

        override fun toSortTypes(): List<MediaSortType> = listOf(POPULARITY_DESC)
    },
    MOST_RATED {

        override fun toSortTypes(): List<MediaSortType> = listOf(RATE_DESC)
    };

    abstract fun toSortTypes(): List<MediaSortType>
}
