package com.aaron.websocket

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
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
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item(span = { GridItemSpan(4) }) {
                        Text(
                            text = "단축키",
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    }

                    items(vm.shortcuts) { shortcut ->
                        RunButton(
                            name = shortcut,
                            onClick = {
                                vm.onClickShortcut(shortcut)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            text = "응용 프로그램",
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    }

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
    Box(
        modifier = Modifier
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 24.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold
        )
    }
}
