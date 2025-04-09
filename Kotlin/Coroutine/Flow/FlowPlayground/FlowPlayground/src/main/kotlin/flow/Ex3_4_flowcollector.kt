package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

fun interface MyFlowCollector {
    suspend fun emit(value: String)
}

fun main() = runBlocking {
    val f: suspend (MyFlowCollector) -> Unit = {
        it.emit("A")
        it.emit("B")
        it.emit("C")
    }

    f { log(it) }
    f { log(it) }
}