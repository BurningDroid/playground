package org.example.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.example.log

/**
 * catch 함수로 업스트림에서 발생하는 예외를 처리할 수 있다.
 */
fun main() = runBlocking {
    val flow = flowOf(1, 2, 3, 4, 5)
    flow.onEach {
        if (it == 3) {
            throw Exception("go to hell")
        }
    }.catch { t ->
        log("catch: $t")
    }.onCompletion {
        log("onCompletion: $it")
    }.collect {
        log("[received] $it")
    }
}