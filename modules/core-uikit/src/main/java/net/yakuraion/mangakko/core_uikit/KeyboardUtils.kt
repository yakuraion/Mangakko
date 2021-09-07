package net.yakuraion.mangakko.core_uikit

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(view: View? = null) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = currentFocus ?: view ?: View(this)
    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
    focusedView.clearFocus()
}

fun Activity.showKeyboard(view: View) {
    view.requestFocus()
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}
