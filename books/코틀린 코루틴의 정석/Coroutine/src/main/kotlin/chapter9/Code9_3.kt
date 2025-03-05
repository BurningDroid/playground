package org.example.chapter9

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.example.Log

fun main(): Unit = runBlocking {
    val result = searchByKeyword("hello world")
    Log.d(result.joinToString())
}

private suspend fun searchByKeyword(keyword: String): Array<String> {
    return supervisorScope {
        val deferredDbResults = async {
            throw Exception("Test Exception")
            searchFromDB(keyword)
        }
        val deferredServerResults = async {
            searchFromServer(keyword)
        }

        val dbResults = try {
            deferredDbResults.await()
        } catch (e: Exception) {
            arrayOf()
        }
        val serverResults = try {
            deferredServerResults.await()
        } catch (e: Exception) {
            arrayOf()
        }

        return@supervisorScope arrayOf(*dbResults, *serverResults)
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