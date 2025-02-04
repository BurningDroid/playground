package org.example.chapter5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main() = runBlocking {
    Log.d("start")
    val participantDeferred1 = async(Dispatchers.IO) {
        getUser1()
    }

    val participantDeferred2 = async(Dispatchers.IO) {
        getUser2()
    }

    val user1: Array<String> = participantDeferred1.await()
    val user2: Array<String> = participantDeferred2.await()

    Log.d("참여자 목록: ${listOf(*user1, *user2)}")
}
