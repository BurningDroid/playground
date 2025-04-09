package org.example.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val flow1 = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    val flow2 = flow {
        (1..3).forEach {
            emit(it)
        }
    }

    val flow3 = flowOf(1, 2, 3)

    val flow4 = listOf(1, 2, 3).asFlow()
}
