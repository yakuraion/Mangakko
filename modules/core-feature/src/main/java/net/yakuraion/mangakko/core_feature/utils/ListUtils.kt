package net.yakuraion.mangakko.core_feature.utils

/**
 * Create a new list where some items will be replaced with new items
 */
fun <T> List<T>.replace(predicate: (T) -> Boolean, newValue: (T) -> T): List<T> {
    return map { item ->
        if (predicate.invoke(item)) {
            newValue(item)
        } else {
            item
        }
    }
}
