package org.example.chapter4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 순차적으로 실행되는 코드
 */
fun main() = runBlocking {
    val updateTokenJob = launch(Dispatchers.IO) {
        println("token update start")
        delay(100L)
        println("token update done")
    }
    val otherJob = launch(Dispatchers.IO) {
        println("other job")
    }

    updateTokenJob.join()

    val apiCall = launch(Dispatchers.IO) {
        println("API Call!")
    }
}