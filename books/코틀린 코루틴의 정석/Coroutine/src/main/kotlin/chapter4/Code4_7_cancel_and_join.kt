package org.example.chapter4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val longJob: Job = launch(Dispatchers.Default) {
        // 작업 실행
    }

    longJob.cancelAndJoin()

//    executeAfterJobCancelled()
}