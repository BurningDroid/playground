package org.example.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.example.log

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking<Unit> {
    produce(capacity = Channel.UNLIMITED) {
        repeat(3) {
            val data = "channel_item_$it"
            log("[Channel] send: $data")
            send(data)
        }
    }

    flow {
        repeat(3) {
            val data = "flow_item_$it"
            log("[Flow] emit: $data")
            emit(data)
        }
    }.collect {}

    delay(1_000)
    log("<<< done >>>")
}