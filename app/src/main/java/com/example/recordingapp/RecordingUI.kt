package com.example.recordingapp

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun VoiceRecordingScreen(
    isRecording: Boolean,
    isStarted: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    onSave: () -> Unit
) {
    // Timer logic
    var seconds by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = isRecording) {
        while (isRecording) {
            delay(1000)
            seconds++
        }
    }

    val timeFormatted = String.format("%02d:%02d", seconds / 60, seconds % 60)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ Custom Mic Pulse Animation here
        MicPulseAnimation(isRecording = isRecording)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = timeFormatted,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!isStarted) {
                Button(onClick = onStart, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                    Text("Start")
                }
            } else {
                Button(
                    onClick = onPause,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                ) {
                    Text(if (isRecording) "Pause" else "Resume")
                }

                Button(
                    onClick = onStop,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Stop")
                }

                Button(
                    onClick = onSave,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Composable
fun MicPulseAnimation(
    isRecording: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulseTransition")

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "pulse"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(180.dp)
    ) {
        // Pulse effect
        if (isRecording) {
            for (i in 1..3) {
                val scale = pulse * i
                val alpha = (1f - pulse).coerceIn(0f, 1f)

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .background(Color.Red.copy(alpha = 0.4f), CircleShape)
                )
            }
        }

        // Inner mic circle
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    if (isRecording) Color.Red else Color.Gray,
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_keyboard_voice_24),
                contentDescription = "Mic",
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
