package net.yakuraion.mangakko.core_uikit.fragment

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

/**
 * Найти среди родительских фрагментов и target фрагмента такой, который будет реализовывать [T].
 *
 * Используется для создания обратной связи между дочерним и родительским/target фрагментами
 * (дочерний фрагмент может вызывать методы родительского фрагмента через интерфейс).
 */
inline fun <reified T> Fragment.getListener(): T? {
    return findListenerByTargetFragment()
        ?: findListenerByParent()
}

/**
 * Аналогично [getListener], однако выбрасывает ошибку в случае, если не найден подходящий фрагмент
 */
inline fun <reified T> Fragment.requireListener(): T {
    return getListener() ?: error(
        "Nor targetFragment, neither parentFragment (or activity) implements ${T::class.java.simpleName}"
    )
}
