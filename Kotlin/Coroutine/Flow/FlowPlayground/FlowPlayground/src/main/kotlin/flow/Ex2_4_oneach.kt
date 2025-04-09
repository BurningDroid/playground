package org.example.flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(3)
    }.onStart { log("onStart") }
        .onEach { log("onEach: $it") }
        .onCompletion { log("onCompletion - throwable: $it") }
        .collect {}
}
