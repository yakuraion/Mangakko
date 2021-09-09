package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaStatus.RELEASING
import net.yakuraion.mangakko.core_network.type.MediaStatus as MediaStatusApi

object MediaStatusToMediaStatusApiMapper {

    fun invoke(value: MediaStatus): MediaStatusApi {
        return when (value) {
            RELEASING -> MediaStatusApi.RELEASING
        }
    }
}
