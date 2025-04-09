package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

suspend fun sendString(
    channel: SendChannel<String>,
    data: String,
    time: Long
) {
    while (true) {
        delay(time)
        channel.send(data)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()

    launch { sendString(channel, "hello", 1_000L) }
    launch { sendString(channel, "! WORLD !", 3_000L) }

    repeat(10) {
        log(channel.receive())
    }

    log("<<< done >>>")
    channel.cancel()
}