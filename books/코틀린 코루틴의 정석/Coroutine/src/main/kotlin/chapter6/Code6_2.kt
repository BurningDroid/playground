package org.example.chapter6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val coroutineContext1 = newSingleThreadContext("MyThread") + CoroutineName("MyCoroutine")
    val coroutineContext2 = newSingleThreadContext("MyThread2") + CoroutineName("MyCoroutine2")

    val newCoroutineContext = coroutineContext1 + coroutineContext2
    launch(newCoroutineContext) {
        Log.d("hello world")
    }
}