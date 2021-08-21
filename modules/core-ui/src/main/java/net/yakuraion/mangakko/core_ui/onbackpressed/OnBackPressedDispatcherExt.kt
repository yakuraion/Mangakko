package net.yakuraion.mangakko.core_ui.onbackpressed

import android.app.Activity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * Create and add a new [OnBackPressedCallback] that calls [onBackPressed] in
 * [OnBackPressedCallback.handleOnBackPressed]. If [OnBackPressedCallback] return false
 * than call next callback in the chain inside [OnBackPressedDispatcher]
 *
 * If an [owner] is specified, the callback will only be added when the Lifecycle is
 * [androidx.lifecycle.Lifecycle.State.STARTED].
 *
 * A default [enabled] state can be supplied.
 */
fun OnBackPressedDispatcher.addCallback(
    activity: Activity,
    owner: LifecycleOwner? = null,
    enabled: Boolean = true,
    onBackPressed: OnBackPressedCallback.() -> Boolean
): OnBackPressedCallback {
    return addCallback(owner, enabled) {
        val intercepted = onBackPressed.invoke(this)
        if (!intercepted) {
            isEnabled = false
            activity.onBackPressed()
            isEnabled = true
        }
    }
}

fun OnBackPressedDispatcher.addCallbackForHideBottomSheetBehavior(
    bottomSheetBehavior: BottomSheetBehavior<*>,
    activity: Activity,
    owner: LifecycleOwner? = null,
    enabled: Boolean = true,
    hideState: Int = BottomSheetBehavior.STATE_COLLAPSED
): OnBackPressedCallback {
    return addCallback(activity, owner, enabled) {
        val visibleStates = setOf(BottomSheetBehavior.STATE_EXPANDED, BottomSheetBehavior.STATE_SETTLING)
        if (bottomSheetBehavior.state in visibleStates) {
            bottomSheetBehavior.state = hideState
            true
        } else {
            false
        }
    }
}
