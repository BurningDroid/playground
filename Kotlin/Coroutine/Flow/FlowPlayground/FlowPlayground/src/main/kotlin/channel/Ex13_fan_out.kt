package org.example.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.example.log

fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
    repeat(10) {
        delay(100)
        send(it)
    }
}

fun CoroutineScope.launchProcessor(id: String, channel: ReceiveChannel<Int>): Job = launch {
    for (msg in channel) {
        log("[$id] received: $msg")
    }
}

fun main() = runBlocking {
    val channel = produceNumbers()
    listOf("A", "B", "C").forEach { id ->
        delay(10)
        launchProcessor(id, channel)
    }
}