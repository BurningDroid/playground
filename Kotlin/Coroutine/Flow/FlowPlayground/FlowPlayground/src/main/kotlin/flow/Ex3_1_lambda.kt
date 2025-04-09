package org.example.flow

import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val f: () -> Unit = {
        log("A")
        log("B")
        log("C")
    }

    f()
    f()
}