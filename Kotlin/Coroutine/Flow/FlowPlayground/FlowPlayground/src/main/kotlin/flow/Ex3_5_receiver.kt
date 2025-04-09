package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val f: suspend MyFlowCollector.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }

    f { log(it) }
    f { log(it) }
}