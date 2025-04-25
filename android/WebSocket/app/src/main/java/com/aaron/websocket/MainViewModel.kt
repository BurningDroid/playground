package com.aaron.websocket

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaron.websocket.client.client
import com.aaron.websocket.model.WsMessage
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.util.reflect.typeInfo
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainViewModel : ViewModel() {

    var connected: Boolean by mutableStateOf(false)
        private set

    val chatList: SnapshotStateList<String> = mutableStateListOf()
    val supportProgramList: SnapshotStateList<WsMessage.App> = mutableStateListOf()
    val shortcuts = listOf(
        "⌘C",
        "⌘V",
        "⌘X",
        "⌘T",
        "⌘W",
        "⌘Q"
    )

    private var webSocketSession: DefaultClientWebSocketSession? = null

    fun connect(url: String = "ws://192.168.207.33:8080/launcher") {
        viewModelScope.launch {
            try {
                client.webSocket(urlString = url) {
                    webSocketSession = this
                    connected = true

                    observeClose()

                    incoming.receiveAsFlow()
                        .onEach { Log.w(TAG, "[spoon][test] connect.receive: ${(it as? Frame.Text)?.readText()}") }
                        .mapNotNull { it as? Frame.Text }
                        .collect { frame ->
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

    private fun onReceiveMessage(frame: Frame.Text) {
        val wsMessage = Json.decodeFromString<WsMessage>(frame.readText())
        when (wsMessage) {
            is WsMessage.Apps -> {
                supportProgramList.clear()
                supportProgramList.addAll(wsMessage.list)
            }

            is WsMessage.App -> {}

            is WsMessage.ShortCut -> {}
        }

        chatList.add(wsMessage.toString())
    }

    fun disconnect() {
        viewModelScope.launch {
            webSocketSession?.close()
            webSocketSession = null
            connected = false
        }
    }

    fun onClickProgram(app: WsMessage.App) {
        viewModelScope.launch {
            webSocketSession?.sendSerialized(app, typeInfo<WsMessage>())
        }
    }

    fun onClickShortcut(shortCut: String) {
        viewModelScope.launch {
            webSocketSession?.sendSerialized(WsMessage.ShortCut(shortCut), typeInfo<WsMessage>())
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}