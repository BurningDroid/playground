package org.example.channel

import kotlinx.coroutines.channels.Channel
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
        val data = channel.receive()
        log("[receiver] received: $data\n")
    }
}