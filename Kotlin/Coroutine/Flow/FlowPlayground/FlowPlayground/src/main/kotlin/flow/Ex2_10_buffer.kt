package org.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.example.log
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.measureTime

fun getAllUserIds(): Flow<Int> {
    return flow {
        repeat(3) {
            delay(200)
            log("Emit!")
            emit(it)
        }
    }
}

suspend fun getProfileFromNetwork(id: Int): String {
    delay(2_000)
    return "Profile[$id]"
}

fun main() = runBlocking {
    val time = measureTime {
        val ids = getAllUserIds()
        ids
            .buffer()
            .map { getProfileFromNetwork(it) }
            .collect {
                log("Got $it")
            }
    }

    log("time: $time")
}