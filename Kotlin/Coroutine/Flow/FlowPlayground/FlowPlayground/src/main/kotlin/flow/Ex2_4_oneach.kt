package org.example.flow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val flow = flowOf(1, 2, 3)
    flow.onStart { log("onStart") }
        .onEach { log("onEach: $it") }
        .onCompletion { log("onCompletion - throwable: $it") }
        .collect {}
}
