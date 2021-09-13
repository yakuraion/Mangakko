package net.yakuraion.mangakko.core_feature.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import net.yakuraion.mangakko.core_feature.ui.livedata.SingleLiveEvent
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Базовая [ViewModel], от которой должны наследоваться все [ViewModel] в приложении
 */
open class BaseViewModel : ViewModel(), CoroutineScope {

    private val scopeJob: Job = SupervisorJob()

    private val errorHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext = scopeJob + Dispatchers.Main + errorHandler

    val errorLiveData: SingleLiveEvent<Throwable> = SingleLiveEvent()

    protected fun handleError(throwable: Throwable) {
        Timber.e(throwable)
        errorLiveData.value = throwable
    }

    override fun onCleared() {
        coroutineContext.cancelChildren()
        super.onCleared()
    }
}
