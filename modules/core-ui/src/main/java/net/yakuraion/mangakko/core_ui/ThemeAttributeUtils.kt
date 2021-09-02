package net.yakuraion.mangakko.core_ui

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
fun Context.resolveColorAttr(@AttrRes attrRes: Int): Int {
    val tv = TypedValue()
    theme.resolveAttribute(attrRes, tv, true)
    return tv.data
}
