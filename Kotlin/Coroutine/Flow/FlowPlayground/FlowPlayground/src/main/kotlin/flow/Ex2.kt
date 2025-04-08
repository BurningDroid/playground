package org.example.flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.example.log

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