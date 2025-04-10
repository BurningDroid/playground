package org.example.flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.time.measureTime

fun main() = runBlocking<Unit> {
    val time = measureTime {
        flow {
            repeat(3) {
                launch {
                    val data = generateNumber()
                    log("[Flow] emit: $data")
                    emit(data)
                }
            }
        }.collect {}
    }

    log("time: $time")
}