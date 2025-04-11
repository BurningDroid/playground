package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log


fun main() = runBlocking<Unit> {
    val vm = LiveViewModel(
//        extraBufferCapacity = 3,
//        onBufferOverflow = BufferOverflow.SUSPEND
    )

    delay(3_000)

    launch {
        vm.walaMsgFlow.collect {
            delay(3000)
            log("[receive1] $it")
        }
    }
}