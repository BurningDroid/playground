package org.example.chapter8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    try {
        launch(CoroutineName("Coroutine1")) {
            throw Exception("Coroutine1에 예외가 발생했습니다")
        }
    } catch (e: Exception) {
        Log.d(e.message ?: "")
    }


    launch(CoroutineName("Coroutine2")) {
        delay(100)
        Log.d("run coroutine2")
    }

    delay(1000)
}