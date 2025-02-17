package org.example.chapter6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val coroutineContext = CoroutineName("MyCoroutine") + Dispatchers.IO
    val name: CoroutineName? = coroutineContext[CoroutineName.Key]
    Log.d("name: $name")
}