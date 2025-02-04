package org.example.chapter5

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking {
    val networkDeferred: Deferred<String> = async(Dispatchers.IO) {
        delay(1000)
        return@async "Dummy Response"
    }

    val result = networkDeferred.await()
    Log.d(result)
}