package org.example.chapter4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val lazyJob: Job = launch(start = CoroutineStart.LAZY) {
        println("lazyJob done")
    }

    println("here 1")
    delay(1_000L)
    println("here 2")

    lazyJob.start()
}