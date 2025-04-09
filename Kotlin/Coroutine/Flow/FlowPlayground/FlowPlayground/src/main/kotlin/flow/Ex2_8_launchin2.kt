package org.example.flow

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.joinAll
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

        val job1 = flow.flowOn(CoroutineName("co1")).launchIn(this)
        val job2 = flow.flowOn(CoroutineName("co2")).launchIn(this)

        joinAll(job1, job2)
    }

    log("time: $time")
}