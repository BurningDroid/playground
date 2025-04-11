package org.example.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.log

class AuthViewModel {

    private val _authFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val authFlow: StateFlow<String?> = _authFlow

    suspend fun login() {
        coroutineScope {
            delay(500)
            _authFlow.value = "jwt-token"
            delay(500)
            _authFlow.emit("jwt-token2")
            delay(500)
            _authFlow.emit("jwt-token2")
            delay(500)
            _authFlow.emit("jwt-token2")
            delay(500)
            _authFlow.update { "jwt-token3" }
        }
    }
}

fun main() = runBlocking<Unit> {
    val vm = AuthViewModel()

    launch {
        vm.authFlow
            .collect { token ->
                log("[collect] token: $token")
            }
    }

    log("[before] token: ${vm.authFlow.value}")

    vm.login()

    log("[after] token: ${vm.authFlow.value}")
}