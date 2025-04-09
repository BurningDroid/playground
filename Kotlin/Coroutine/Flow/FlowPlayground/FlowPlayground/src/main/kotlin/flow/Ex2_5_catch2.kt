package org.example.flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.catch { t ->
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
