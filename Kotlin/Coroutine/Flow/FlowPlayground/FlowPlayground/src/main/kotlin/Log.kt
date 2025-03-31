package org.example

import java.text.SimpleDateFormat
import java.util.*

private val sdf = SimpleDateFormat("HH:mm:ss.sss")

fun log(message: String) {
    println("${sdf.format(Date())} [${Thread.currentThread().name}] $message")
}