package org.example.chapter3

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch(context = Dispatchers.IO.limitedParallelism(1000)) {
        repeat(200) { count ->
            launch {
                Thread.sleep(1000)
                println("[${Thread.currentThread().name}] 코루틴 실행")
            }
        }
    }
}