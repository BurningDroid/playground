package org.example.chapter6

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val myJob = Job()
    val coroutineContext = Dispatchers.IO + myJob

    launch(coroutineContext) {
        Log.d("hello world")
    }
}