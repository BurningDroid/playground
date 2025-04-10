package org.example.flow

import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.time.measureTime

fun main() = runBlocking<Unit> {
    val time = measureTime {
        channelFlow {
            repeat(3) {
                launch {
                    val data = generateNumber()
                    log("[ChannelFlow] emit: $data")
                    send(data)
                }
            }
        }.collect {}
    }

    log("time: $time")
}