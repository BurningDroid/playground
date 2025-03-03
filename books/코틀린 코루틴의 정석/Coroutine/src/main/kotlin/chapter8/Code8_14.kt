package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    async(CoroutineName("Coroutine1")) {
        throw Exception("Coroutine1에 예외가 발생했습니다")
    }

    launch(CoroutineName("Coroutine2")) {
        delay(100)
        Log.d("run coroutine2")
    }
}
