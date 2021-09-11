package net.yakuraion.mangakko.core_uikit

import android.app.Activity
import android.util.DisplayMetrics

fun Activity.getScreenMetrics(): DisplayMetrics {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}
