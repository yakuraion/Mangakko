package net.yakuraion.mangakko.core_ui.fragment

import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
inline fun <reified T> Fragment.findListenerByTargetFragment(): T? {
    return if (T::class.java.isInstance(targetFragment)) targetFragment as T? else null
}

inline fun <reified T> Fragment.findListenerByParent(): T? {
    var fragment = this
    while (fragment.parentFragment != null) {
        fragment = fragment.requireParentFragment()
        if (T::class.java.isInstance(fragment)) {
            return fragment as T
        }
    }
    return if (T::class.java.isInstance(activity)) activity as T? else null
}

inline fun <reified T> Fragment.requireListener(): T {
    return findListenerByTargetFragment()
        ?: findListenerByParent()
        ?: error(
            "Nor targetFragment, neither parentFragment (or activity) implements ${T::class.java.simpleName}"
        )
}

inline fun <reified T> Fragment.getListener(): T? {
    return findListenerByTargetFragment()
        ?: findListenerByParent()
}
