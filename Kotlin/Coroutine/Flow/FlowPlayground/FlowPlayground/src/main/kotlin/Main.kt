package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun main() {
    val flow: Flow<Int> = flowOf(1, 2, 3)

    CoroutineScope(Dispatchers.IO + CoroutineName("hello")).launch {
        flow.collect { it ->
            log("Hello: $it")
        }
    }

    Thread.sleep(1000)
}