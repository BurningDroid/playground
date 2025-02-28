package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    supervisorScope {
        launch(CoroutineName("Coroutine1")) {
            launch(CoroutineName("Coroutine3")) {
                throw Exception()
            }
            delay(100)
            Log.d("run")
        }

        launch(CoroutineName("Coroutine2")) {
            delay(100)
            Log.d("run")
        }
    }
}