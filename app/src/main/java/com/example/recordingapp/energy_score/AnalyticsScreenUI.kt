package com.example.recordingapp.energy_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsScreenUI() {
    val eScoreFactorsList = FakeDatabase.getEScoreData()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top: Line graph takes 50% of height
        LineGraphScreen(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth()
        )

        // Spacer is optional now since we're using weights
        // Spacer(modifier = Modifier.height(24.dp))

        // Bottom: Progress lines take 50% of height
        ProgressLinesUI(
            eScoreFactorsList,
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
        )
    }
}
