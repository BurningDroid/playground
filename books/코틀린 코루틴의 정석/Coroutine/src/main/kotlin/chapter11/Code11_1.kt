package org.example.chapter11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.example.Log

var count = 0

fun main(): Unit = runBlocking {
    withContext(Dispatchers.Default) {
        repeat(10_000) {
            launch {
                count += 1
            }
        }
    }

    Log.d("count: $count")
}