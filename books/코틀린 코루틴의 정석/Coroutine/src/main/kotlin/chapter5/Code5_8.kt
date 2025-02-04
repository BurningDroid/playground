package org.example.chapter5

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking {
    Log.d("start: async-await")
    val deferred: Deferred<String> = async(Dispatchers.IO) {
        Log.d("in-progress")
        delay(1000)
        return@async "Dummy Response"
    }

    Log.d(deferred.await())
}