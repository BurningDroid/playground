package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.random.Random
import kotlin.random.nextInt

fun getTemperatures(): Flow<Int> {
    return flow {
        while (true) {
            emit(Random.nextInt(-10..30))
            delay(300)
        }
    }
}

fun main() = runBlocking {
    val temps = getTemperatures()
    temps
        .onEach { log("onEach: $it") }
        .conflate()
//        .buffer(Channel.CONFLATED)
//        .buffer(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        .collect {
            log("Collected: $it")
            delay(1_000)
        }
}