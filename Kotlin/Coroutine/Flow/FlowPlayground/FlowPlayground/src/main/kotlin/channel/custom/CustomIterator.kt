package org.example.channel.custom

import org.example.log

class CustomIterator {

    private var currIdx = 0
    val list: List<Int> = (1..100).toList()

    suspend operator fun hasNext(): Boolean {
        return currIdx < 100
    }

    operator fun next(): Int {
        return list[currIdx++]
    }
}

class MyChannel {
    operator fun iterator(): CustomIterator {
        return CustomIterator()
    }
}


suspend fun main() {
    val myChannel = MyChannel()
    for (value in myChannel) {
        log("value: $value")
    }
}