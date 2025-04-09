package org.example.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.example.log

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val searchQueries = flow {
        emit("h")
        delay(100)

        emit("he")
        delay(100)

        emit("hel")
        delay(400) // 400ms!

        emit("hell")
        delay(100)

        emit("hello")
        delay(100)

        emit("hello ")
        delay(400) // 400ms!

        emit("hello w")
        delay(100)

        emit("hello wo")
        delay(100)

        emit("hello wor")
        delay(400) // 400ms!

        emit("hello worl")
        delay(100)

        emit("hello world")
        delay(100)

        emit("hello world!")
        delay(100)
    }

    searchQueries
        .debounce(300)
        .collect { query ->
            log("API 요청: $query")
        }
}
