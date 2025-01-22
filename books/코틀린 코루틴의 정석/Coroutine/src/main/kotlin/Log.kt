package org.example

import java.text.SimpleDateFormat
import java.util.*

object Log {

    private val sdf = SimpleDateFormat("HH:mm:ss.sss")

    fun d(message: String) {
        println("${sdf.format(Date())}\t[${Thread.currentThread().name}]\t$message")
    }
}