package org.example.chapter11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import org.example.Log

val mutex = Mutex()

fun main(): Unit = runBlocking {
    withContext(Dispatchers.Default) {
        repeat(10_000) {
            launch {
                mutex.lock()
                count += 1
                mutex.unlock()
            }
        }
    }

    Log.d("count: $count")
}