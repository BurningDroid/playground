package org.example.chapter5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main() = runBlocking {
    Log.d("start")
    val deferred1 = async(Dispatchers.IO) {
        getUser1()
    }

    val deferred2 = async(Dispatchers.IO) {
        getUser2()
    }

    val result: List<Array<String>> = awaitAll(deferred1, deferred2)

    Log.d("참여자 목록: ${listOf(*result[0], *result[1])}")
}
