package chapter10

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.example.Log

fun main(): Unit = runBlocking {
    launch {
        while(true) {
            Log.d("child coroutine run")
            yield()
        }
    }

    while(true) {
        Log.d("child coroutine run")
        yield()
    }
}