package net.yakuraion.mangakko.core_ui

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

var Fragment.statusBarColor: Int
    get() = requireActivity().window.statusBarColor
    set(value) {
        requireActivity().window.statusBarColor = value
    }

var Fragment.isLightStatusBar: Boolean
    @RequiresApi(Build.VERSION_CODES.M)
    get() = requireActivity().window.decorView.systemUiVisibility and
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR != 0
    @RequiresApi(Build.VERSION_CODES.M)
    set(value) {
        val view = requireActivity().window.decorView
        view.systemUiVisibility = if (value) {
            view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
