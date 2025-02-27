package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val newRootJob = Job()
    launch(CoroutineName("Coroutine1") + newRootJob) {
        launch(CoroutineName("Coroutine3")) {
            delay(100)
            Log.d("코루틴 실행")
        }
        launch(CoroutineName("Coroutine4")) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    launch(CoroutineName("Coroutine2") + newRootJob) {
        launch(CoroutineName("Coroutine5") + Job()) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    delay(50)
    newRootJob.cancel()
    delay(1000)
}