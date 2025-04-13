package org.example.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import org.example.log

/**
 * flow를 try-catch로 감싸 예외 처리
 */
fun main() = runBlocking {
    try {
        val flow = flowOf(1, 2, 3, 4, 5)
        flow.catch { t ->
            log("caught1: $t")
        }.onCompletion {
            log("onCompletion: $it")
        }.collect {
            if (it == 3) {
                throw Exception("go to hell!")
            }
            log("[received] $it")
        }
    } catch (t: Throwable) {
        log("caught2: $t")
    }
}
