package org.example.flow

import org.example.log

fun main() {
    val list = buildList {
        repeat(3) {
            add("list_item_$it")
            log("[list] add item")
        }
    }.map {
        log("[list] processing")
        it.uppercase()
    }

    val sequence = sequence {
        repeat(3) {
            yield("sequence_item_$it")
            log("[sequence] add item")
        }
    }.map {
        log("[sequence] processing")
        it.uppercase()
    }

//    sequence.toList()
}