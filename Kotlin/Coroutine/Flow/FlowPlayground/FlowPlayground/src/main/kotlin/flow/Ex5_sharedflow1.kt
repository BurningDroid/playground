package org.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.example.log
import java.text.SimpleDateFormat
import java.util.*

class LiveViewModel(
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss")
) {

    private val _walaMsgFlow: MutableSharedFlow<String> = MutableSharedFlow(
        replay = replay,
        extraBufferCapacity = extraBufferCapacity,
        onBufferOverflow = onBufferOverflow
    )
    val walaMsgFlow: SharedFlow<String> = _walaMsgFlow

    private var count = 0

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while(true) {
                delay(1_000)
                val timeText = sdf.format(Date())
                _walaMsgFlow.emit("$timeText - $count")
                log("[emit] $count")
                count++
            }
        }
    }
}

fun main() = runBlocking<Unit> {
    val vm = LiveViewModel()
    vm.walaMsgFlow.collect {
        log("[receive] $it")
    }
}