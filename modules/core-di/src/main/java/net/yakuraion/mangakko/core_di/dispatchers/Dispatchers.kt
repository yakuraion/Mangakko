package net.yakuraion.mangakko.core_di.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Обертка над [CoroutineDispatcher], позволяющая подменять [CoroutineDispatcher] на тестовые во время
 * написания тестов.
 *
 * @see <a
 * href="https://developer.android.com/kotlin/coroutines/coroutines-best-practices#inject-dispatchers">
 * Inject Dispatchers
 * </a>
 */
interface Dispatchers {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val compute: CoroutineDispatcher
}
