package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val letters = flow {
    log("Emitting A!")
    emit("A")

    delay(200L)

    log("Emitting B!")
    emit("B")
}

fun main() = runBlocking {
    letters.collect {
        log("\tCollecting $it")
        delay(500L)
    }

}