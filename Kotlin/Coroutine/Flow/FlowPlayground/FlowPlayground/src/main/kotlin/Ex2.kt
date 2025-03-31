package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val flow = flow {
        log("emit: monday")
        emit("Monday")

        log("emit: tuesday")
        emit("Tuesday")

        log("emit: wednesday")
        emit("Wednesday")
    }.onCompletion { t ->
        log("exception: $t")
    }

    flow.take(2)
        .collect {
            log("\tcollect: $it")
        }
}