package org.example.chapter4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 순차 처리 되지 않는 코드
 */
fun main() = runBlocking {
    val updateTokenJob = launch(Dispatchers.IO) {
        println("token update start")
        delay(100L)
        println("token update done")
    }

    val apiCall = launch(Dispatchers.IO) {
        println("API Call!")
    }
}