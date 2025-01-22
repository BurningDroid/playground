package org.example.chapter4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

/**
 * 순차 처리 되지 않는 코드
 */
fun main() = runBlocking {
    val updateTokenJob = launch(Dispatchers.IO) {
        Log.d("token update start")
        delay(100L)
        Log.d("token update done")
    }

    val apiCall = launch(Dispatchers.IO) {
        Log.d("API Call!")
    }
}