package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {

    val channel = Channel<Int>(onUndeliveredElement = {
        log("!!! onUndeliveredElement: $it !!!")
    })

    val producerJob = launch { // 생산자 코루틴
        repeat(10) {
            delay(1_000)
            log("[sender] send: $it")
            channel.send(it)
        }
        channel.close()
        log("[sender] <<< done >>>")
    }

    val consumerJob1 = launch { // 소비자 코루틴
        val resp1 = channel.receive()
        log("[R1] received: $resp1\n")

        val resp2 = channel.receive()
        log("[R1] received: $resp2\n")
        channel.cancel()

        val resp3 = channel.receive()
        log("[R1] received: $resp3")
        log("[R1] <<< done >>>")
    }

    val consumerJob2 = launch { // 소비자 코루틴
        val resp1 = channel.receive()
        log("[R2] received: $resp1\n")

        val resp2 = channel.receive()
        log("[R2] received: $resp2\n")

        val resp3 = channel.receive()
        log("[R2] received: $resp3")
        log("[R2] <<< done >>>")
    }

    joinAll(producerJob, consumerJob1, consumerJob2)
    log("All Done!")
}