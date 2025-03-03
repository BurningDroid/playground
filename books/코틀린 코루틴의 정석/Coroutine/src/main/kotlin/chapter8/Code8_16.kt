package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking(CoroutineName("Parent Coroutine")) {
    launch(CoroutineName("Child Coroutine")) {
        withTimeout(1000) {
            delay(2000)
            Log.d("run child coroutine")
        }
    }

    delay(100)
    Log.d("run parent coroutine")
}