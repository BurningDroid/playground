package org.example.chapter8

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    supervisorScope {
        val deferred: Deferred<String> = async(CoroutineName("Coroutine1")) {
            throw Exception("Coroutine1에 예외가 발생했습니다")
        }

        try {
            deferred.await()
        } catch (e: Exception) {
            Log.d(e.message ?: "")
        }
    }

    delay(1000L)
}
