package org.example.flow

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.time.measureTime

fun main() = runBlocking {
    val time = measureTime {
        val flow = flow {
            delay(1000)
            emit("hello")

            delay(1000)
            emit("world")
        }.onEach {
            log("onEach: $it")
        }

        flow.flowOn(CoroutineName("co1")).collect {}
        flow.flowOn(CoroutineName("co2")).collect {}
    }

    log("time: $time")
}