package net.yakuraion.mangakko.core_uikit

import android.view.WindowManager
import androidx.fragment.app.Fragment

fun Fragment.setSoftInputModeAsAdjustResize() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.setSoftInputModeAsAdjustPan() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}
