package org.example.chapter7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val coroutineContext = newSingleThreadContext("MyThread") + CoroutineName("ParentCoroutine")
    launch(coroutineContext) {
        Log.d("hello world 1")

        launch(CoroutineName("ChildCoroutine")) {
            Log.d("hello world 2")
        }
    }
}