package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val supervisorJob = SupervisorJob()
    launch(CoroutineName("Coroutine1") + supervisorJob) {
        launch(CoroutineName("Coroutine3")) {
            throw Exception()
        }
        delay(100)
        Log.d("run")
    }

    launch(CoroutineName("Coroutine2") + supervisorJob) {
        delay(100)
        Log.d("run")
    }

    delay(200)
}