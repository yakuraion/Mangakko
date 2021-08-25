package net.yakuraion.mangakko.core_testutils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers

@ExperimentalCoroutinesApi
class TestDispatchers : Dispatchers {

    override val io: CoroutineDispatcher = TestCoroutineDispatcher()
    override val main: CoroutineDispatcher = TestCoroutineDispatcher()
    override val compute: CoroutineDispatcher = TestCoroutineDispatcher()
}
