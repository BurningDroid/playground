package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

interface MyFlow {
    suspend fun collect(collector: MyFlowCollector)
}

fun main() = runBlocking<Unit> {
    val builder: suspend MyFlowCollector.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }

    val flow: MyFlow = object : MyFlow {
        override suspend fun collect(collector: MyFlowCollector) {
            collector.builder()
        }
    }

    flow.collect { log(it) }
    flow.collect { log(it) }
}