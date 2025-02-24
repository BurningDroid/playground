package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

fun main(): Unit = runBlocking {
    val newScope: CoroutineScope = CoroutineScope(CoroutineName("MyCoroutine") + Dispatchers.IO)
    newScope.cancel()
    newScope.launch(CoroutineName("LaunchCoroutine")) {
        Log.d("coroutine name: ${this.coroutineContext[CoroutineName]}")

        val launchJob = this.coroutineContext[Job]
        val newScopeJob = newScope.coroutineContext[Job]

        Log.d("launchJob.parent === newScopeJob: ${launchJob?.parent === newScopeJob}")
        Log.d("newScopeJob.children.contains(launchJob): ${newScopeJob?.children?.contains(launchJob)}")
    }

    Thread.sleep(1000)
}