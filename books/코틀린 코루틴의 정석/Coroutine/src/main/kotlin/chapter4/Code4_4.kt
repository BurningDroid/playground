package org.example.chapter4

import kotlinx.coroutines.*
import org.example.Log

fun main() = runBlocking<Unit> {
    Log.d("start!")

    val convertImageJob1: Job = launch(Dispatchers.Default) {
        Log.d("image1 converting start")
        delay(3000L)
        Log.d("image1 converting done")
    }

    val convertImageJob2: Job = launch(Dispatchers.Default) {
        Log.d("image2 converting start")
        delay(3000L)
        Log.d("image2 converting done")
    }

    joinAll(convertImageJob1, convertImageJob2)
    Log.d("join done!")

    val uploadImageJob: Job = launch(Dispatchers.Default) {
        delay(3000L)
        Log.d("img1, 2 upload done")
    }
}