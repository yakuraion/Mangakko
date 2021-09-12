package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_network.type.MediaType
import net.yakuraion.mangakko.core_network.type.MediaType.ANIME
import net.yakuraion.mangakko.core_network.type.MediaType.MANGA

object MediaTypeApiToMediaTypeMapper {

    fun invoke(value: MediaType): net.yakuraion.mangakko.core_entity.MediaType {
        return when (value) {
            ANIME -> net.yakuraion.mangakko.core_entity.MediaType.ANIME
            MANGA -> net.yakuraion.mangakko.core_entity.MediaType.MANGA
            else -> net.yakuraion.mangakko.core_entity.MediaType.ANIME
        }
    }
}
