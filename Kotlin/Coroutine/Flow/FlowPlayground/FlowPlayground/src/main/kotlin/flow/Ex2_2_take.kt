package org.example.flow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    flowOf("Monday", "Tuesday", "Wednesday")
        .onCompletion { t ->
            log("onCompletion: $t")
        }
        .take(2)
        .collect {
            log("collect: $it")
        }
}
