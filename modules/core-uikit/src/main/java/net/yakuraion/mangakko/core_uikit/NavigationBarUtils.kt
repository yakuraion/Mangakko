package net.yakuraion.mangakko.core_uikit

import android.os.Build
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

var Fragment.navigationBarColor: Int
    get() = requireActivity().window.navigationBarColor
    set(value) {
        requireActivity().window.navigationBarColor = value
    }

var Fragment.isLightNavigationBar: Boolean
    @RequiresApi(Build.VERSION_CODES.O_MR1)
    get() = requireActivity().window.decorView.systemUiVisibility and
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR != 0
    @RequiresApi(Build.VERSION_CODES.O_MR1)
    set(value) {
        val view = requireActivity().window.decorView
        view.systemUiVisibility = if (value) {
            view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        } or FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    }
