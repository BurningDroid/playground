package org.example.chapter11

import kotlinx.coroutines.*
import org.example.Log

val countChangeDispatcher = newSingleThreadContext("CountChangeThread")

fun main(): Unit = runBlocking {
    withContext(Dispatchers.Default) {
        repeat(10_000) {
            launch {
                withContext(countChangeDispatcher) {
                    count += 1
                }
            }
        }
    }

    Log.d("count: $count")
}