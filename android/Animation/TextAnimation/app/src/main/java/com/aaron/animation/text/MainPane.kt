package com.aaron.animation.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun MainPane() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var onOff by remember { mutableStateOf(false) }
            LaunchedEffect(onOff) {
                if (onOff) {
                    delay(10_000)
                    onOff = false
                }
            }

            if (onOff) {
                FadeInEffectText("라이브 스케줄로 방송 일정을 알리세요")

                TypingEffectText("라이브 스케줄로 방송 일정을 알리세요")
            }

            if (!onOff) {
                Button(onClick = {
                    onOff = true
                }) {
                    Text(text = "Run")
                }
            }
        }
    }
}

@Composable
fun TypingEffectText(text: String) {
    var animatedText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        repeat(text.length) {
            animatedText += text[it].toString()
            delay(25)
        }
    }
    Text(
        text = animatedText,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
fun FadeInEffectText(text: String) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(text) {
        visible = true
    }

    val enter: EnterTransition = fadeIn(tween(3000)) + slideInVertically(
        animationSpec = tween(1000, easing = FastOutLinearInEasing),
        initialOffsetY = { -it }
    )

    AnimatedVisibility(
        visible = visible,
        enter = enter
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview
@Composable
fun PreviewFadeInEffectText() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FadeInEffectText("라이브 스케줄로 방송 일정을 알리세요")
    }
}