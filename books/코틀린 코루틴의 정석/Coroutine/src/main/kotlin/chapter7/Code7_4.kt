package org.example.chapter7

import kotlinx.coroutines.*
import org.example.Log
import kotlin.coroutines.CoroutineContext

class CustomCoroutineScope: CoroutineScope {
    override val coroutineContext: CoroutineContext = Job() + newSingleThreadContext("CustomScopeThread")
}

fun main(): Unit = runBlocking {
    val coroutineScope = CustomCoroutineScope()
    coroutineScope.launch {
        delay(100)
        Log.d("scopeJob: ${coroutineScope.coroutineContext[Job]}")
        Log.d("myJob: ${this.coroutineContext[Job]}")
        Log.d("parentJob: ${this.coroutineContext[Job]?.parent}")
        Log.d("코루틴 실행 완료")
    }

    Thread.sleep(1000)
}