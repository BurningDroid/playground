package org.example.flow

fun main() {
    val list = buildList {
        repeat(3) {
            add("item_$it")
            println("[list] add item")
        }
    }.map {
        println("[list] processing")
        it.uppercase()
    }

    val sequence: Sequence<String> = sequence {
        repeat(3) {
            yield("item_$it")
            println("[sequence] add item")
        }
    }.map {
        println("[sequence] processing")
        it.uppercase()
    }
    sequence.toList()
}