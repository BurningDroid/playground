package org.example.channel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
//    val channel = Channel<Int>()
    val channel = Channel<Int>(
        capacity = Channel.CONFLATED,
        onBufferOverflow = BufferOverflow.SUSPEND,
        onUndeliveredElement = {}
    )

    launch {
        repeat(10) {
            println("[sender] send: $it, \t$channel")
            channel.send(it)
        }
        println("[sender] <<< done >>>\n")
//        channel.close()
    }

    val receiveJob = launch {
        for (value in channel) {
            println("[receiver] $value")
            delay(1_000)
        }

        println("[receiver] <<< done >>>\n")
    }

    receiveJob.join()
    println("<<< channel complete!>>>")
}