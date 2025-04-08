package org.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.example.log

class ColdFlow: Flow<String> {
    override suspend fun collect(collector: FlowCollector<String>) {
        log("Emitting A!")
        collector.emit("A")

        log("Emitting B!")
        collector.emit("B")
    }
}

class ColdCollector: FlowCollector<String> {
    override suspend fun emit(value: String) {
        log("\tCollecting $value")
        delay(3000L)
    }
}

fun main() = runBlocking {
    ColdFlow().collect(ColdCollector())
}