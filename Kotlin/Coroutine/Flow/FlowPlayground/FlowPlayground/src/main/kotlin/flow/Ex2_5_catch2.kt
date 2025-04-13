package org.example.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.example.log

/**
 * catch 함수는 업스트림에서 발생하는 예외만 잡을 수 있다.
 * 다운 스트림에서 발생하는 예외는 잡을 수 없다.
 */
fun main() = runBlocking {
    val flow = flowOf(1, 2, 3, 4, 5)
    flow.catch { t ->
        log("catch: $t")
    }.onEach {
        if (it == 3) {
            throw Exception("go to hell")
        }
    }.onCompletion {
        log("onCompletion: $it")
    }.collect {
        log("[received] $it")
    }
}
