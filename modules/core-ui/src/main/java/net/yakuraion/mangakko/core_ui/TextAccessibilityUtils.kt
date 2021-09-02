package net.yakuraion.mangakko.core_ui

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils

@ColorInt
fun calculateTextColorByBackground(context: Context, @ColorInt bgColor: Int): Int {
    val resId = if (isDarkColor(bgColor)) R.color.white else R.color.text_dark
    return ContextCompat.getColor(context, resId)
}

private fun isDarkColor(@ColorInt color: Int): Boolean = ColorUtils.calculateLuminance(color) < 0.5
