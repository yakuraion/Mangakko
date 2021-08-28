package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_network.QueryPageMediaQuery

object MediaApiToMediaMapper {

    fun invoke(value: QueryPageMediaQuery.Medium): Media {
        return Media(
            id = value.id(),
            title = value.title()?.romaji().orEmpty(),
            imageUrl = value.coverImage()?.large().orEmpty()
        )
    }
}
