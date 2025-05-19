package com.example.recordingapp

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun VoiceListScreen(
    recordedVoices: List<String>, // list of file names or titles
    onRecordClick: () -> Unit
) {
    var isMusicPlaying = remember {
        true
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onRecordClick) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_keyboard_voice_24),
                    contentDescription = "Record"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(recordedVoices) { voice ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isMusicPlaying = true
                            Log.i("RecordingAppUI", "card view: $isMusicPlaying")
                        }
                ) {
                    Text(
                        text = voice,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        if (isMusicPlaying) {
            ShowMusicPlayingCard()
        }
        Log.i("RecordingListUI", "$isMusicPlaying")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMusicPlayingCard() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        modifier = Modifier.fillMaxSize(0.5f)
            .shadow(5.dp)
    ) {
        Text(text = "Playing...")
    }
}
