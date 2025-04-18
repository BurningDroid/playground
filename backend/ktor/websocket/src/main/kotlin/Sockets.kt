package com.example.ktor

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

private val programList = listOf(
    "Google Chrome",
    "Android Studio",
    "Slack",
    "Github Desktop",
    "Visual Studio Code",
)

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/launcher") {
            val sessionId = this.hashCode().toString()
            println("[ws] 📡 Client connected: $sessionId")
            outgoing.send(Frame.Text(programList.joinToString()))

            while (true) {
                when (val frame = incoming.receive()) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        println("[ws] Text: $text")
                        onReceiveMessage(outgoing, text)
                    }

                    is Frame.Binary -> {
                        println("[ws] Binary")
                    }

                    is Frame.Close -> {
                        println("[ws] Close")
                    }

                    is Frame.Ping -> {
                        println("[ws] Ping")
                    }

                    is Frame.Pong -> {
                        println("[ws] Pong")
                    }
                }
            }
        }
    }
}

private suspend fun onReceiveMessage(outgoing: SendChannel<Frame>, text: String) {
    if (programList.contains(text)) {
        launchProgram(text)
        outgoing.send(Frame.Text("$text is launched"))
        return
    } else if (text.startsWith("⌘")) {
        runShortcut(text)
        outgoing.send(Frame.Text("$text is operated"))
        return
    }

    outgoing.send(Frame.Text("Not Found: $text"))
}

private suspend fun runShortcut(text: String) {
    val key = text.split("⌘")[1].lowercase()
    withContext(Dispatchers.IO) {
        Runtime.getRuntime().exec(
            arrayOf(
                "osascript",
                "-e",
                "tell application \"System Events\" to keystroke \"$key\" using command down"
            )
        )
    }
}

private fun launchProgram(program: String) {
    try {
        ProcessBuilder("open", "-a", program)
            .start()
    } catch (e: Exception) {
        println("Failed to launch Chrome: ${e.localizedMessage}")
    }
}
