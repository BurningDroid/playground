package org.example.chapter5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.Log

suspend fun getUser1(): Array<String> {
    delay(1000)
    return arrayOf("james", "jason")
}

suspend fun getUser2(): Array<String> {
    delay(1000)
    return arrayOf("jenny")
}

fun main() = runBlocking {
    Log.d("start")
    val participantDeferred1 = async(Dispatchers.IO) {
        getUser1()
    }
    val user1: Array<String> = participantDeferred1.await()

    val participantDeferred2 = async(Dispatchers.IO) {
        getUser2()
    }
    val user2: Array<String> = participantDeferred2.await()

    Log.d("참여자 목록: ${listOf(*user1, *user2)}")
}
