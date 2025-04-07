package org.example.flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = produce(capacity = Channel.UNLIMITED) {
        repeat(3) {
            val data = "channel_item_$it"
            println("send: $data")
            send(data)
        }
    }

    val flow = flow {
        repeat(3) {
            val data = "flow_item_$it"
            println("emit: $data")
            emit(data)
        }
    }

    delay(1_000)
}