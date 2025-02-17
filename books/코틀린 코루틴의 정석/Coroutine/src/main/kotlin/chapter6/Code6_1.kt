package org.example.chapter6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val coroutineContext = newSingleThreadContext("MyThread") + CoroutineName("MyCoroutine")
    launch(coroutineContext) {
        Log.d("hello world 1")
    }

    val newCoroutineContext = coroutineContext + CoroutineName("NewCoroutine")
    launch(newCoroutineContext) {
        Log.d("hello world 2")
    }
}