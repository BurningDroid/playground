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
        repeat(3) {
            delay(1_000)
            log("[sender] send: $it")
            channel.send(it)
        }
        channel.close()
        log("[sender] <<< done >>>")
    }

    val consumerJob = launch { // 소비자 코루틴
        val resp1 = channel.receive()
        log("[receiver] received[1]: $resp1\n")

        val resp2 = channel.receive()
        log("[receiver] received[2]: $resp2\n")
//        channel.cancel()

        val resp3 = channel.receive()
        log("[receiver] received[3]: $resp3\n")

        log("[receiver] <<< done >>>")
    }

    joinAll(producerJob, consumerJob)
    log("All Done!")
}