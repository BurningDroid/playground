package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.log


fun main() = runBlocking<Unit> {
    val vm = LiveViewModel(replay = 3)

    delay(3_000)

    vm.walaMsgFlow.collect {
        log("[receive] $it")
    }
}