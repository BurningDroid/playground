package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    launch(CoroutineName("Coroutine1")) {
        val newJob = Job()
        launch(CoroutineName("Coroutine2") + newJob) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    delay(100)
}