package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { context, t ->
        Log.d("예외 발생: $t")
    }

    launch(CoroutineName("Coroutine1") + exceptionHandler) {
        throw Exception("Coroutine1에 예외가 발생했습니다")
    }

    delay(1000)
}