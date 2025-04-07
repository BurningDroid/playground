package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {

    val channel = Channel<Int>()

    launch { // 생산자 코루틴
        repeat(3) {
            log("[sender] send: $it")
            channel.send(it)
        }
    }

    launch { // 소비자 코루틴
        for (value in channel) {
            log("[receiver] received: $value\n")
            delay(1_000)
        }
    }
}