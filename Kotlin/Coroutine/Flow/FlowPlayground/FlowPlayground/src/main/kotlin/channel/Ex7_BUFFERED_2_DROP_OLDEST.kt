package org.example.channel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    val channel = Channel<Int>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST // capacity가 BUFFERED이고 onBufferOverflow가 SUSPEND가 아니라면 버퍼 크기는 1
    )

    val producerJob = launch {
        repeat(5) {
            log("[sender] send: $it, \t$channel")
            channel.send(it)
        }
        log("[sender] <<< done >>>")
        channel.close()
    }

    val consumerJob = launch {
        for (value in channel) {
            log("[receiver] $value")
            delay(1_000)
        }

        log("[receiver] <<< done >>>")
    }

    joinAll(producerJob, consumerJob)
    log("All Done!")
}