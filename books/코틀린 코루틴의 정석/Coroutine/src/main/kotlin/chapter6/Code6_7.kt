package org.example.chapter6

import kotlinx.coroutines.*
import org.example.Log

@OptIn(ExperimentalStdlibApi::class)
fun main(): Unit = runBlocking {
    val coroutineName = CoroutineName("MyCoroutine")
    val dispatcher = Dispatchers.IO
    val job = Job()
    val coroutineContext = coroutineName + dispatcher + job

    Log.d("Before")
    Log.d(" name: ${coroutineContext[CoroutineName]}")
    Log.d(" dispatcher: ${coroutineContext[CoroutineDispatcher]}")
    Log.d(" job: ${coroutineContext[Job]}")
    Log.d("\n")

    val newCoroutineContext = coroutineContext.minusKey(CoroutineName)

    Log.d("After")
    Log.d(" name: ${newCoroutineContext[CoroutineName]}")
    Log.d(" dispatcher: ${newCoroutineContext[CoroutineDispatcher]}")
    Log.d(" job: ${newCoroutineContext[Job]}")
}