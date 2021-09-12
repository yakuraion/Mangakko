package net.yakuraion.mangakko.core_repositories_impl.mappers

import android.graphics.Color
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_network.QueryPageMediaQuery

object MediaApiToMediaMapper {

    fun invoke(value: QueryPageMediaQuery.Medium): Media {
        return Media(
            id = value.id(),
            title = value.title()?.romaji().orEmpty(),
            type = MediaTypeApiToMediaTypeMapper.invoke(value.type()!!),
            imageUrl = value.coverImage()?.extraLarge().orEmpty(),
            mainColor = value.coverImage()?.color()?.let { Color.parseColor(it) },
            score = value.meanScore()
        )
    }
}
