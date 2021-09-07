package net.yakuraion.mangakko.core_uikit

import androidx.fragment.app.FragmentManager

fun FragmentManager.clearBackStack() {
    repeat(backStackEntryCount) {
        popBackStack()
    }
}
