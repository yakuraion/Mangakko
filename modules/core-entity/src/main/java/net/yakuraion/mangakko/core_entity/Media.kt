package net.yakuraion.mangakko.core_entity

import androidx.annotation.ColorInt
import java.io.Serializable

data class Media(
    val id: Int,
    val title: String,
    val type: MediaType,
    val imageUrl: String,
    @ColorInt val mainColor: Int?,
    val score: Int?
) : Serializable
