package org.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
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

        val job1 = launch { flow.flowOn(CoroutineName("co1")).collect {} }
        val job2 = launch { flow.flowOn(CoroutineName("co2")).collect {} }

        joinAll(job1, job2)
    }

    log("time: $time")
}