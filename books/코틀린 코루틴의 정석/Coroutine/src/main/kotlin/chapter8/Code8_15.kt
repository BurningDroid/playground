package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    launch(CoroutineName("Coroutine1")) {
        launch(CoroutineName("Coroutine2")) {
            throw CancellationException("Coroutine1에 예외가 발생했습니다")
        }

        delay(100)
        Log.d("run coroutine2")
    }
}