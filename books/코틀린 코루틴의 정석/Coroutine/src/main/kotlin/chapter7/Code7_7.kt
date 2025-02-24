package org.example.chapter7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    launch(CoroutineName("Coroutine1")) {
        launch(CoroutineName("Coroutine3")) {
            delay(100)
            Log.d("코루틴 실행")
        }
        launch(CoroutineName("Coroutine4")) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    launch(CoroutineName("Coroutine2")) {
        launch(CoroutineName("Coroutine5")) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    delay(1000)
}