package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {
    val flow = generalFlow {
        log("send: 1")
        emit(1)

        log("send: 2")
        emit(2)

        log("send: 3")
        emit(3)
    }

    flow.collect {
        delay(1_000)
        log("received: value_$it")
    }
}