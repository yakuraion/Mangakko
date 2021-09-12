package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_entity.MediaType.ANIME
import net.yakuraion.mangakko.core_entity.MediaType.MANGA

object MediaTypeToMediaTypeApiMapper {

    fun invoke(value: MediaType): net.yakuraion.mangakko.core_network.type.MediaType {
        return when (value) {
            ANIME -> net.yakuraion.mangakko.core_network.type.MediaType.ANIME
            MANGA -> net.yakuraion.mangakko.core_network.type.MediaType.MANGA
        }
    }
}
