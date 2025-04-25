package com.aaron.websocket.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class WsMessage {
    @Serializable
    @SerialName("apps")
    data class Apps(val list: List<App>) : WsMessage()

    @Serializable
    @SerialName("app")
    data class App(val name: String) : WsMessage()

    @Serializable
    @SerialName("shortcut")
    data class ShortCut(val value: String) : WsMessage()
}