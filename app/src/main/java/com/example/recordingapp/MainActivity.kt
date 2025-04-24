package com.example.recordingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recordingapp.ui.theme.RecordingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var showRecordingUI by remember { mutableStateOf(false) }
    var isRecording by remember { mutableStateOf(false) }
    var isStarted by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf("00:00") }
    val voices = remember { mutableStateListOf("Voice_1.mp3", "Voice_2.mp3") }

    if (showRecordingUI) {
        VoiceRecordingScreen(
            isRecording = isRecording,
            isStarted = isStarted,
            onStart = {
                isStarted = true
                isRecording = true
                // Start timer here
            },
            onPause = {
                isRecording = !isRecording
                // Pause or resume timer
            },
            onStop = {
                isRecording = false
                isStarted = false
                // Stop timer
            },
            onSave = {
                voices.add("Voice_${voices.size + 1}.mp3")
                showRecordingUI = false
                isRecording = false
                isStarted = false
            }
        )
    } else {
        VoiceListScreen(recordedVoices = voices) {
            showRecordingUI = true
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecordingAppTheme {
        Greeting("Android")
    }
}