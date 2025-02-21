package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    val coroutineContext = newSingleThreadContext("MyThread") + CoroutineName("ParentCoroutine")
    launch(coroutineContext) {
        val parentJob = this.coroutineContext[Job]
        Log.d("parentJob.parent == null: \t${parentJob?.parent == null}")

        launch(CoroutineName("ChildCoroutine")) {
            val childJob = this.coroutineContext[Job]


            Log.d("parentJob.contains(childJob): \t${parentJob?.children?.contains(childJob)}")
            Log.d("childJob.parent == parentJob: \t${childJob?.parent == parentJob}")
        }
    }
}