package com.example.recordingapp.energy_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recordingapp.energy_score.LineGraph

@Composable
fun LineGraphScreen(modifier: Modifier = Modifier) {
    val percentageValues = listOf(
        0.1f, 0.4f, 0.6f, 0.3f, 0.7f,
        0.5f, 0.9f, 0.8f, 0.6f, 1.0f
    )

    Column(modifier = modifier) {
        Text(
            text = "Performance Line Chart",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LineGraph(data = percentageValues)
    }
}
