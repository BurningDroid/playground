package org.example.channel

import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {

    val channel = produce<String> {
        listOf("HELLO", "WORLD", "WELCOME!").forEach {
            delay(1_000)
            log("[sender] send: $it")
            send(it)
        }
        log("[sender] <<< done >>>")
    }

    val consumerJob = launch { // 소비자 코루틴
        for (data in channel) {
            log("[receiver] received: $data\n")
        }

        log("[receiver] <<< done >>>")
    }

    joinAll(consumerJob)
    log("All Done!")
}