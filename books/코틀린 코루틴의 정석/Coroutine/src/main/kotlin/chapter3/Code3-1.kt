package org.example.chapter3

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * 3.3.1 단일 스레드 디스패처 만들기
 */
fun main() = runBlocking<Unit> {
    val dispatcher: CoroutineDispatcher = newSingleThreadContext(name = "단일_스레드_디스패처")
}