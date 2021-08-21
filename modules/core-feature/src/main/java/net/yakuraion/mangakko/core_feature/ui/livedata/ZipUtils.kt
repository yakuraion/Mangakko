package net.yakuraion.mangakko.core_feature.ui.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <A, B, C> combineLatestLiveData(
    liveData1: LiveData<A>,
    liveData2: LiveData<B>,
    transform: (A, B) -> C
): LiveData<C> {
    val mediator = MediatorLiveData<C>()
    val zippedObject: MutableMap<LiveData<*>, Any?> = mutableMapOf()

    fun tryEmitNewValue() {
        if (zippedObject.containsKey(liveData1) && zippedObject.containsKey(liveData2)) {
            val valueA = zippedObject[liveData1] as A
            val valueB = zippedObject[liveData2] as B
            mediator.value = transform.invoke(valueA, valueB)
        }
    }

    mediator.addSource(liveData1) { valueA ->
        zippedObject[liveData1] = valueA
        tryEmitNewValue()
    }

    mediator.addSource(liveData2) { valueB ->
        zippedObject[liveData2] = valueB
        tryEmitNewValue()
    }

    return mediator
}

fun <A, B, C, D> combineLatestLiveData(
    liveData1: LiveData<A>,
    liveData2: LiveData<B>,
    liveData3: LiveData<C>,
    transform: (A, B, C) -> D
): LiveData<D> {
    val mediator = MediatorLiveData<D>()
    val zippedObject: MutableMap<LiveData<*>, Any?> = mutableMapOf()

    fun tryEmitNewValue() {
        if (
            zippedObject.containsKey(liveData1) &&
            zippedObject.containsKey(liveData2) &&
            zippedObject.containsKey(liveData3)
        ) {
            val valueA = zippedObject[liveData1] as A
            val valueB = zippedObject[liveData2] as B
            val valueC = zippedObject[liveData3] as C
            mediator.value = transform.invoke(valueA, valueB, valueC)
        }
    }

    mediator.addSource(liveData1) { valueA ->
        zippedObject[liveData1] = valueA
        tryEmitNewValue()
    }

    mediator.addSource(liveData2) { valueB ->
        zippedObject[liveData2] = valueB
        tryEmitNewValue()
    }

    mediator.addSource(liveData3) { valueC ->
        zippedObject[liveData3] = valueC
        tryEmitNewValue()
    }

    return mediator
}
