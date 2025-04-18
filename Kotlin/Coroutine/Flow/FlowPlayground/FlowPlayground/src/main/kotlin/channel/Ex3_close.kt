package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {

    val channel = Channel<String>()

    val producerJob = launch { // 생산자 코루틴
        listOf("HELLO", "WORLD", "WELCOME!").forEach {
            delay(1_000)
            log("[sender] send: $it")
            channel.send(it)
        }
        channel.close()
        log("[sender] <<< done >>>")
    }

    val consumerJob = launch { // 소비자 코루틴
        for (data in channel) {
            log("[receiver] received: $data\n")
        }

        log("[receiver] <<< done >>>")
    }

    joinAll(producerJob, consumerJob)
    log("All Done!")
}