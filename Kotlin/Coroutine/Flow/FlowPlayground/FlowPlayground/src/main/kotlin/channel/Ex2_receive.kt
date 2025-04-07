package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {

    val channel = Channel<Int>()
    log("channel is created")

    launch { // 소비자 코루틴
        log("start receiving")

        for (value in channel) {
            log("[receiver] received: $value\n")
            delay(1_000)
        }
    }
}