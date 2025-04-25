package com.aaron.websocket.client

import com.aaron.websocket.model.WsMessage
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val client = HttpClient(CIO) {
    wsConfig()
}

private fun HttpClientConfig<CIOEngineConfig>.wsConfig() {
    val json = Json {
        isLenient = true
        serializersModule = SerializersModule {
            polymorphic(WsMessage::class) {
                subclass(WsMessage.Apps::class, WsMessage.Apps.serializer())
                subclass(WsMessage.App::class, WsMessage.App.serializer())
                subclass(WsMessage.ShortCut::class, WsMessage.ShortCut.serializer())
            }
        }
        classDiscriminator = "type"
        ignoreUnknownKeys = true
    }
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(json)
    }
    install(ContentNegotiation) {
        json(json)
    }
}