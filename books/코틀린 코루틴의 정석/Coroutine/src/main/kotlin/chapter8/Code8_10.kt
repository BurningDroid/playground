package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { context, t ->
        Log.d("예외 발생: $t")
    }

    val supervisedScope = CoroutineScope(SupervisorJob() + exceptionHandler)
    supervisedScope.apply {
        launch(CoroutineName("Coroutine1")) {
            throw Exception("Coroutine1에 예외가 발생했습니다")
        }

        launch(CoroutineName("Coroutine2")) {
            delay(100)
            Log.d("run coroutine")
        }
    }

    delay(1000)
}