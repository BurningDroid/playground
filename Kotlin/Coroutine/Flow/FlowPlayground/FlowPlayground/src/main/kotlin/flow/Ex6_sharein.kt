package org.example.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

fun main() = runBlocking<Unit> {
    val flow: Flow<Int> = (1..3)
        .asFlow()
        .onEach { log("emit: $it") }
        .shareIn(scope = this, started = SharingStarted.Lazily)

    launch {
        flow
            .collect {
                log("[collect1] $it")
            }
    }

    launch {
        flow
            .collect {
                log("[collect2] $it")
            }
    }
}