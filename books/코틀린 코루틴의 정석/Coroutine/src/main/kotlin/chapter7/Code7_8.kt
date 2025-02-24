package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val newScope = CoroutineScope(Dispatchers.IO)
    newScope.launch(CoroutineName("Coroutine1")) {
        launch(CoroutineName("Coroutine3")) {
            delay(100)
            Log.d("코루틴 실행")
        }
        launch(CoroutineName("Coroutine4")) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    newScope.launch(CoroutineName("Coroutine2")) {
        launch(CoroutineName("Coroutine5")) {
            delay(100)
            Log.d("코루틴 실행")
        }
    }

    delay(1000)
}