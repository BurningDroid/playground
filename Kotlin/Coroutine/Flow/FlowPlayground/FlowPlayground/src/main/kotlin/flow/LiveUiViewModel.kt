package org.example.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LiveUiViewModel(
    private val setupLive: SetupLive,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    private val _toastFlow: MutableSharedFlow<String> = MutableSharedFlow()
    val toastFlow: SharedFlow<String> = _toastFlow

    fun startLive() {
        CoroutineScope(ioDispatcher).launch {
            val result = setupLive.setup()
            if (result) {
                _toastFlow.emit("라이브가 시작되었어요!")
            } else {
                _toastFlow.emit("에러가 발생했어요!")
            }
        }
    }
}
