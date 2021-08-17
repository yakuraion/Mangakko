package net.yakuraion.mangakko.core_ui

import androidx.fragment.app.FragmentManager

fun FragmentManager.clearBackStack() {
    repeat(backStackEntryCount) {
        popBackStack()
    }
}
