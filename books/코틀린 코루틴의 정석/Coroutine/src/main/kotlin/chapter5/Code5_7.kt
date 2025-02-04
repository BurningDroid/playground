package org.example.chapter5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.example.Log

fun main() = runBlocking {
    Log.d("start: withContext")
    val result: String = withContext(Dispatchers.IO) {
        Log.d("in-progress")
        delay(1000)
        return@withContext "Dummy Response"
    }

    Log.d(result)
}