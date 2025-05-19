package com.example.recordingapp.energy_score

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun LineGraph(
    data: List<Float>, // must be between 0f and 1f
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(16.dp)
) {
    Canvas(modifier = modifier) {
        val spacing = size.width / (data.size - 1)
        val maxHeight = size.height

        // Draw axes
        drawLine(
            color = Color.Gray,
            start = Offset(0f, maxHeight),
            end = Offset(size.width, maxHeight),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Gray,
            start = Offset(0f, 0f),
            end = Offset(0f, maxHeight),
            strokeWidth = 2f
        )

        // Draw lines between points
        val points = data.mapIndexed { index, value ->
            Offset(x = index * spacing, y = maxHeight - (value * maxHeight))
        }

        for (i in 0 until points.size - 1) {
            drawLine(
                color = Color.Blue,
                start = points[i],
                end = points[i + 1],
                strokeWidth = 4f
            )
        }

        // Draw points
        points.forEachIndexed { index, point ->
            drawCircle(
                color = Color.Red,
                radius = 6f,
                center = point
            )

            // Draw labels
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${(data[index] * 100).toInt()}%",
                    point.x,
                    point.y - 10,
                    Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 24f
                        textAlign = Paint.Align.CENTER
                    }
                )
                drawText(
                    "${index + 1}",
                    point.x,
                    maxHeight + 30f,
                    Paint().apply {
                        color = android.graphics.Color.DKGRAY
                        textSize = 24f
                        textAlign = Paint.Align.CENTER
                    }
                )
            }
        }
    }
}
