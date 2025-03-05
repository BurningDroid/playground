package chapter10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val job = launch {
        Log.d("1")
        delay(1000)
        Log.d("2")
    }

    Log.d("3")
    job.join()
    Log.d("4")
}