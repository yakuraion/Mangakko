package net.yakuraion.mangakko.core_entity

import androidx.annotation.ColorInt

data class MediaDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    @ColorInt val mainColor: Int,
    val description: String
)
