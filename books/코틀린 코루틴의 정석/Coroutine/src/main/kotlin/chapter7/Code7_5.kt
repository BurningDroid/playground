package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    CoroutineScope(Dispatchers.IO).launch {
        delay(100)
        Log.d("코루틴 실행 완료")
    }

    Thread.sleep(1000)
}