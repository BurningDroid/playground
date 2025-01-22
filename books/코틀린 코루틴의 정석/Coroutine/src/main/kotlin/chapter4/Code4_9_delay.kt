package org.example.chapter4

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking<Unit> {
    val whileJob: Job = launch(Dispatchers.Default) {
        while(true) {
            Log.d("run again")
            delay(1L)
        }
    }

    delay(100)

    whileJob.cancel()
}