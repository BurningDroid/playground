package org.example.chapter3

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * 3.3.2 멀티 스레드 디스패처 만들기
 */
fun main() = runBlocking<Unit> {
    val dispatcher: CoroutineDispatcher = newFixedThreadPoolContext(
        nThreads = 2,
        name = "멀티_스레드_디스패처"
    )
}