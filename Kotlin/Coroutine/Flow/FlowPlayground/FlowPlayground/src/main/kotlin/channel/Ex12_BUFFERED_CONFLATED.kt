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
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val producerJob = launch {
        repeat(5) {
            channel.send(it)
            log("[sender] sent: $it, \t$channel")
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
