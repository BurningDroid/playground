package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

fun interface GeneralFlowCollector<T> {
    suspend fun emit(value: T)
}

interface GeneralFlow<T> {
    suspend fun collect(collector: GeneralFlowCollector<T>)
}

fun <T> generalFlow(
    builder: suspend GeneralFlowCollector<T>.() -> Unit
): GeneralFlow<T> = object : GeneralFlow<T> {
    override suspend fun collect(collector: GeneralFlowCollector<T>) {
        collector.builder()
    }
}

fun main() = runBlocking<Unit> {
    val flow = generalFlow {
        emit(1)
        emit(2)
        emit(3)
    }

    flow.collect {
        log("value_$it")
    }
    flow.collect {
        log("value_$it")
    }
}