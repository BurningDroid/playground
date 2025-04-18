package com.aaron.websocket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainPane(vm: MainViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            if (!vm.connected) {
                Button(onClick = {
                    vm.connect()
                }) {
                    Text(text = "Connect")
                }
            } else {
                Button(onClick = {
                    vm.disconnect()
                }) {
                    Text(text = "Disconnect")
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(vm.shortcuts) { shortcut ->
                        RunButton(
                            name = shortcut,
                            onClick = {
                                vm.onClickShortcut(shortcut)
                            }
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(vm.supportProgramList) { programName ->
                        RunButton(
                            name = programName,
                            onClick = {
                                vm.onClickProgram(programName)
                            }
                        )
                    }
                }
            }

            LazyColumn(reverseLayout = true) {
                items(vm.chatList) { chat ->
                    Text(text = chat)
                }
            }
        }
    }
}

@Composable
fun RunButton(name: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = name)
    }
}
