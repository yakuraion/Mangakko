package net.yakuraion.mangakko.core_feature.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseStateImpl<STATE : BaseState<STATE, OWNER>, OWNER : BaseOwner<OWNER, STATE>> :
    BaseState<STATE, OWNER>, CoroutineScope {

    override lateinit var coroutineContext: CoroutineContext

    override var owner: OWNER? = null

    override fun onEnter() {
        Timber.d("%s onEnter", getName())
        coroutineContext = (owner as CoroutineScope).coroutineContext
    }

    override fun onExit() {
        coroutineContext.cancelChildren()
        Timber.d("%s onExit", getName())
    }
}
