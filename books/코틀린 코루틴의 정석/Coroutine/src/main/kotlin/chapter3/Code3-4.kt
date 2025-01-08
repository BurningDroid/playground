package org.example.chapter3

import kotlinx.coroutines.*

/**
 * 3.4.1.2 멀티 스레드 디스패처 사용해 코루틴 실행하기
 */
fun main() = runBlocking<Unit> {
    val dispatcher: CoroutineDispatcher = newFixedThreadPoolContext(nThreads = 3, name = "멀티_스레드_디스패처")

    repeat(100) { count ->
        launch(context = dispatcher) {
            println("[${Thread.currentThread().name}] run $count")
        }
    }
}