package net.yakuraion.mangakko.core_repositories_impl.mappers

import android.graphics.Color
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.text.Html
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
            type = MediaTypeApiToMediaTypeMapper.invoke(value.type()!!),
            imageUrl = value.coverImage()?.extraLarge().orEmpty(),
            mainColor = value.coverImage()?.color()?.let { Color.parseColor(it) },
            score = value.meanScore(),
            description = value.description().orEmpty().removeHtmlTags(),
            rateRank = value.getRankOnAllTime(RATED),
            popularityRank = value.getRankOnAllTime(POPULAR)
        )
    }

    private fun QueryMediaDetailsQuery.Media.getRankOnAllTime(type: MediaRankType): Int? {
        return rankings()?.find { it.allTime() == true && it.type() == type }?.rank()
    }

    private fun String.removeHtmlTags(): String {
        return if (VERSION.SDK_INT >= VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(this).toString()
        }
    }
}
