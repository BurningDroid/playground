package org.example.flow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking {
    flowOf("Monday", "Tuesday", "Wednesday")
        .transform { day ->
            emit(day.uppercase())
            emit(day.lowercase())
        }
        .collect {
            log("collect: $it")
        }
}
