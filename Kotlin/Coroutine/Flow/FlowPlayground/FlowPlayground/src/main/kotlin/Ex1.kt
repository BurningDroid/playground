package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform

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