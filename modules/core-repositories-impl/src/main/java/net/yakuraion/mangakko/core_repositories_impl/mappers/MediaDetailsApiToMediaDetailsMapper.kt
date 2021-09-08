package net.yakuraion.mangakko.core_repositories_impl.mappers

import android.graphics.Color
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_network.QueryMediaDetailsQuery
import net.yakuraion.mangakko.core_network.type.MediaRankType
import net.yakuraion.mangakko.core_network.type.MediaRankType.POPULAR
import net.yakuraion.mangakko.core_network.type.MediaRankType.RATED

object MediaDetailsApiToMediaDetailsMapper {

    fun invoke(value: QueryMediaDetailsQuery.Media): MediaDetails {
        return MediaDetails(
            id = value.id(),
            title = value.title()?.romaji().orEmpty(),
            imageUrl = value.coverImage()?.extraLarge().orEmpty(),
            mainColor = value.coverImage()?.color()?.let { Color.parseColor(it) },
            score = value.meanScore(),
            description = value.description().orEmpty().replace("<br>", ""),
            rateRank = value.getRankOnAllTime(RATED),
            popularityRank = value.getRankOnAllTime(POPULAR)
        )
    }

    private fun QueryMediaDetailsQuery.Media.getRankOnAllTime(type: MediaRankType): Int? {
        return rankings()?.find { it.allTime() == true && it.type() == type }?.rank()
    }
}
