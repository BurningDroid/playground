package org.example.chapter9

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.Log

fun main(): Unit = runBlocking {
    val result = searchByKeyword("hello world")
    Log.d(result.joinToString())
}

private suspend fun searchByKeyword(keyword: String): Array<String> {
    return coroutineScope {
        val dbResults = async {
            searchFromDB(keyword)
        }
        val serverResults = async {
            searchFromServer(keyword)
        }
        return@coroutineScope arrayOf(*dbResults.await(), *serverResults.await())
    }
}

private suspend fun searchFromDB(keyword: String): Array<String> {
    delay(1000)
    return arrayOf("[db]${keyword}1", "[db]${keyword}1")
}

private suspend fun searchFromServer(keyword: String): Array<String> {
    delay(1000)
    return arrayOf("[server]${keyword}1", "[server]${keyword}1")
}