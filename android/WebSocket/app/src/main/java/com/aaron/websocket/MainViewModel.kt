package com.aaron.websocket

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var connected: Boolean by mutableStateOf(false)
        private set

    val chatList: SnapshotStateList<String> = mutableStateListOf()
    val supportProgramList: SnapshotStateList<String> = mutableStateListOf()
    val shortcuts = listOf(
        "⌘C",
        "⌘V",
        "⌘X",
        "⌘T",
        "⌘W",
        "⌘Q"
    )

    private val client = HttpClient(CIO) {
        install(WebSockets)
    }
    private var webSocketSession: DefaultClientWebSocketSession? = null

    fun connect(url: String = "ws://192.168.207.33:8080/launcher") {
        viewModelScope.launch {
            try {
                client.webSocket(urlString = url) {
                    webSocketSession = this
                    connected = true

                    observeClose()

                    for (frame in incoming) {
                        onReceiveMessage(frame)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "[ws] connect - failed: ${e.localizedMessage}", e)
                onDisconnected()
            }
        }
    }

    private fun DefaultClientWebSocketSession.observeClose() {
        launch {
            try {
                val reason = closeReason.await()
                Log.w(TAG, "[spoon][test] disconnected - reason: $reason - ")
                onDisconnected()
            } catch (e: Exception) {
                onDisconnected()
                Log.w(TAG, "[spoon][test] disconnected - exception: ${e.message}", e)
            }
        }
    }

    private fun onDisconnected() {
        connected = false
        webSocketSession = null
    }

    private fun onReceiveMessage(frame: Frame) {
        when (frame) {
            is Frame.Text -> {
                val text = frame.readText()
                if (text.contains(",")) {
                    supportProgramList.clear()
                    supportProgramList.addAll(text.split(",").map(String::trim))
                } else {
                    chatList.add(frame.readText())
                }
            }

            else -> {}
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            webSocketSession?.close()
            webSocketSession = null
            connected = false
        }
    }

    fun onClickProgram(programName: String) {
        viewModelScope.launch {
            webSocketSession?.send(programName)
        }
    }

    fun onClickShortcut(shortcut: String) {
        viewModelScope.launch {
            webSocketSession?.send(shortcut)
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}