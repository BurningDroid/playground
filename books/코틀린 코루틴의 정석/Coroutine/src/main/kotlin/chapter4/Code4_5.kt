package org.example.chapter4

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking<Unit> {
    val lazyJob: Job = launch(start = CoroutineStart.LAZY) {
        Log.d("lazyJob start!")
        delay(2_000L)
        Log.d("lazyJob done!")
    }

    Log.d("lazyJob is created")

    delay(1_000L)

    lazyJob.start()

    Log.d("lazyJob is started")
}