package com.aaron.adaptiveui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.adaptiveui.ui.theme.AdaptiveUiTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveUiTheme {
                val navigator = rememberListDetailPaneScaffoldNavigator<MyItem>()
                BackHandler(navigator.canNavigateBack()) {
                    navigator.navigateBack()
                }

                ListDetailPaneScaffold(
                    directive = navigator.scaffoldDirective,
                    value = navigator.scaffoldValue,
                    listPane = {
                        AnimatedPane {
                            MenuList(
                                menus = listOf("Kotlin", "Android", "KMP", "Compose"),
                                onItemClick = { item ->
                                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                                }
                            )
                        }
                    },
                    detailPane = {
                        AnimatedPane {
                            navigator.currentDestination?.content?.let {
                                DetailsPane(
                                    data = it,
                                    onClickNavigateUp = navigator::navigateBack
                                )
                            }
                        }
                    },
                    modifier = Modifier.statusBarsPadding()
                )
            }
        }
    }

    @Composable
    private fun MenuList(
        menus: List<String>,
        onItemClick: (MyItem) -> Unit
    ) {
        Column {
            menus.forEach {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onItemClick(MyItem(it.hashCode(), it)) })
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }

    @Composable
    private fun DetailsPane(
        data: MyItem,
        onClickNavigateUp: () -> Unit
    ) {
        Scaffold(
            topBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = onClickNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }

                    Text(
                        text = data.data,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Text("Hello ${data.data}!")
            }
        }
    }
}
