package org.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.example.log

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