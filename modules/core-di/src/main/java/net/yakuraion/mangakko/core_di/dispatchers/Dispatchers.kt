package net.yakuraion.mangakko.core_di.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val compute: CoroutineDispatcher
}
