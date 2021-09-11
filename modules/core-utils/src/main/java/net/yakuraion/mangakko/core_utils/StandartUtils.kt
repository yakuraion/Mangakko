package net.yakuraion.mangakko.core_utils

inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
    if (condition) {
        apply(block)
    }
    return this
}
