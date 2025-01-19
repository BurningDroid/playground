package org.example.chapter4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val convertImageJob1: Job = launch(Dispatchers.Default) {
        Thread.sleep(1000L)
        println("image1 converting done")
    }
    val convertImageJob2: Job = launch(Dispatchers.Default) {
        Thread.sleep(1000L)
        println("image2 converting done")
    }

    joinAll(convertImageJob1, convertImageJob2)

    val uploadImageJob: Job = launch(Dispatchers.Default) {
        println("img1, 2 upload done")
    }
}