package org.example.chapter3

import kotlinx.coroutines.*

/**
 * 3.4.2 부모 코루틴의 CoroutineDispatcher 사용해 자식 코루틴 실행하기
 */
fun main() = runBlocking<Unit> {
    val dispatcher: CoroutineDispatcher = newFixedThreadPoolContext(nThreads = 2, name = "멀티_스레드_디스패처")

    launch(context = dispatcher) {
        println("[${Thread.currentThread().name}] 부모 코루틴 실행")

        repeat(5) { count ->
            launch {
                println("[${Thread.currentThread().name}] 자식 코루틴 실행 $count")
            }
        }
    }
}