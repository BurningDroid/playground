package org.example.chapter6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.example.Log
import kotlin.coroutines.CoroutineContext

fun main(): Unit = runBlocking {
    val coroutineName = CoroutineName("MyCoroutine")
    val coroutineContext = coroutineName + Dispatchers.IO
    val name: CoroutineContext.Element? = coroutineContext[coroutineName.key]
    Log.d("name: $name")
}