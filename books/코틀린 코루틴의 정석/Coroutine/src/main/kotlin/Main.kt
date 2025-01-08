package org.example

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    println("[${Thread.currentThread().name}] run")

    launch(CoroutineName("Co1")) {
        println("[${Thread.currentThread().name}] run 2")
    }

    launch(CoroutineName("Co2")) {
        println("[${Thread.currentThread().name}] run 3")
    }
}