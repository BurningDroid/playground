package org.example.channel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking<Unit> {

    val channel = produce(
//        capacity = Channel.RENDEZVOUS
        capacity = Channel.UNLIMITED
    ) {
        repeat(10) {
            println("[sender] send: $it, \t$channel")
            send(it)
        }
    }

    launch {
        for (value in channel) {
            println("[receiver] $value")
            delay(1_000)
        }
    }
}