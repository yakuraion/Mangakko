package net.yakuraion.mangakko.core_entity

import androidx.annotation.ColorInt
import java.io.Serializable

data class Media(
    val id: Int,
    val title: String,
    val imageUrl: String,
    @ColorInt val mainColor: Int?
) : Serializable
