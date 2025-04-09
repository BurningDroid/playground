package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val f: suspend () -> Unit = {
        log("A")
        delay(1_000)
        log("B")
        delay(1_000)
        log("C")
        delay(1_000)
    }

    f()
    f()
}