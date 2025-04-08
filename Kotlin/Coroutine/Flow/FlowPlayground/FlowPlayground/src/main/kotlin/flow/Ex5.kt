package org.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.example.log

fun main() = runBlocking {
    flow {
        log("Emitting A!")
        emit("A")

        delay(200L)

        log("Emitting B!")
        emit("B")
    }.flowOn(Dispatchers.IO + CoroutineName("[1]emit coroutine"))
        .map {
            log("Emitting map: $it")
            it.lowercase()
        }
        .flowOn(Dispatchers.IO + CoroutineName("[2]emit coroutine"))
        .collect {
            withContext(Dispatchers.Default + CoroutineName("collect coroutine")) {
                log("\tCollecting $it")
                delay(500L)
            }
        }
}