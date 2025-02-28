package org.example.chapter8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    launch(CoroutineName("Coroutine1")) {

        launch(CoroutineName("Coroutine3")) {
            throw Exception()
        }

        launch(CoroutineName("Coroutine2")) {
            delay(100)
            Log.d("hello world Coroutine2")
        }
    }
}