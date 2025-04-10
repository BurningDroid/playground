package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.random.Random
import kotlin.time.measureTime

suspend fun generateNumber(): Int {
    delay(1_000)
    return Random.nextInt()
}

fun main() = runBlocking<Unit> {
    val time = measureTime {
        flow {
            repeat(3) {
                val data = generateNumber()
                log("[Flow] emit: $data")
                emit(data)
            }
        }.collect {}
    }

    log("time: $time")
}