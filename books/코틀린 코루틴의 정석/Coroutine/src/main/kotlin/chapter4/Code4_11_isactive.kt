package org.example.chapter4

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking<Unit> {
    val whileJob: Job = launch(Dispatchers.Default) {
        while(this.isActive) {
            Log.d("run again(active check)")
        }
    }

    delay(100)

    whileJob.cancel()
}