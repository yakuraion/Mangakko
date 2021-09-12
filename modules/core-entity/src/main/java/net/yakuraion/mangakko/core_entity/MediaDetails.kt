package net.yakuraion.mangakko.core_entity

import androidx.annotation.ColorInt

data class MediaDetails(
    val id: Int,
    val title: String,
    val type: MediaType,
    val imageUrl: String,
    @ColorInt val mainColor: Int?,
    val score: Int?,
    val description: String,
    val rateRank: Int?,
    val popularityRank: Int?
) {

    fun toMedia(): Media {
        return Media(
            id = id,
            title = title,
            type = type,
            imageUrl = imageUrl,
            mainColor = mainColor,
            score = score
        )
    }
}
