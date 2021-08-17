package net.yakuraion.mangakko.core_ui

import android.content.Context
import android.content.Intent

fun Context.shareLink(shareLink: String, title: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareLink)
    }
    startActivity(Intent.createChooser(shareIntent, title))
}
