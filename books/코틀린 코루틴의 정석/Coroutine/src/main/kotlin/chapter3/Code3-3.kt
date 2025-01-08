package org.example.chapter3

import kotlinx.coroutines.*

/**
 * 3.4.1.1 단일 스레드 디스패처 사용해 코루틴 실행하기
 */
fun main() = runBlocking<Unit> {
    val dispatcher: CoroutineDispatcher = newSingleThreadContext(name = "싱글_스레드_디스패처")
    launch(context = dispatcher) {
        println("[${Thread.currentThread().name}] run")
    }
}