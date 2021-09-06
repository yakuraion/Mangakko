package net.yakuraion.mangakko.core_repositories_impl.mappers

import android.graphics.Color
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_network.QueryMediaDetailsQuery

object MediaDetailsApiToMediaDetailsMapper {

    fun invoke(value: QueryMediaDetailsQuery.Media): MediaDetails {
        return MediaDetails(
            id = value.id(),
            title = value.title()?.romaji().orEmpty(),
            imageUrl = value.coverImage()?.extraLarge().orEmpty(),
            mainColor = value.coverImage()?.color()?.let { Color.parseColor(it) },
            description = value.description().orEmpty().replace("<br>", "")
        )
    }
}
