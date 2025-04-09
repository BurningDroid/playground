package org.example.flow

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val flow = flow {
        log("emit data")
        emit("hello world")
    }

    flow
//        .flowOn(CoroutineName("emit_coroutine"))
        .map {
            log("here is map")
            it.uppercase()
        }
        .flowOn(CoroutineName("map_coroutine"))
        .collect {
            log("[received] $it")
        }
}