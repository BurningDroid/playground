package org.example.chapter4

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking<Unit> {
    val longJob: Job = launch(Dispatchers.Default) {
        while(true) {
            delay(1000L)
            Log.d("run again")
        }
    }

    delay(3500)

    longJob.cancel()

    Log.d("DONE!")
}