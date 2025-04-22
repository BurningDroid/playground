package org.example.flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {
    val flow = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    flow.collect {
        log("collect: $it")
    }
}
