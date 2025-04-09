package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

fun myFlow(
    builder: suspend MyFlowCollector.() -> Unit
): MyFlow = object : MyFlow {
    override suspend fun collect(collector: MyFlowCollector) {
        collector.builder()
    }
}

fun main() = runBlocking<Unit> {
    val flow = myFlow {
        emit("A")
        emit("B")
        emit("C")
    }

    flow.collect { log(it) }
    flow.collect { log(it) }
}