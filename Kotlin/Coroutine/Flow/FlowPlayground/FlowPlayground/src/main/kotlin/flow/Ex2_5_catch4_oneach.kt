package org.example.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.example.log

/**
 * collect() 연산을 onEach로 옮기고
 * 그 아래 catch() 함수로 예외 처리
 */
fun main() = runBlocking {
    val flow = flowOf(1, 2, 3, 4, 5)
    flow.onCompletion {
        log("onCompletion: $it")
    }.onEach {
        if (it == 3) {
            throw Exception("go to hell!")
        }
        log("[received] $it")
    }.catch { t ->
        log("caught1: $t")
    }.collect()
}
