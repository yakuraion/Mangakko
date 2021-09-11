package net.yakuraion.mangakko.core_uikit

import android.content.res.Resources
import kotlin.math.roundToInt

fun Float.dpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}

fun Float.dpToPxInt(): Int {
    return dpToPx().roundToInt()
}

fun Float.pxToDp(): Float {
    return this / Resources.getSystem().displayMetrics.density
}

fun Float.spToPx(): Float {
    return this * Resources.getSystem().displayMetrics.scaledDensity
}

fun Float.spToPxInt(): Int {
    return spToPx().roundToInt()
}
