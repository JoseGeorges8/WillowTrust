package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.data.models.PieChartEntry

fun calculateStartAngles(entries: List<PieChartEntry>): List<Float> {
    var totalPercentage = 0f
    return entries.map { entry ->
        val startAngle = totalPercentage * 360
        totalPercentage += entry.percentage
        startAngle
    }
}

@Composable
fun PieChart(entries: List<PieChartEntry>) {
    Canvas(modifier = Modifier.size(300.dp)) {
        val startAngles = calculateStartAngles(entries)
        entries.forEachIndexed { index, entry ->
            drawArc(
                color = entry.color,
                startAngle = startAngles[index],
                sweepAngle = entry.percentage * 360f,
                useCenter = true,
                topLeft = Offset.Zero,
                size = this.size
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicPieChart() {
    val entries = listOf(
        PieChartEntry(Color.Red, 0.5f),
        PieChartEntry(Color.Green, 0.3f),
        PieChartEntry(Color.Blue, 0.2f)
    )
    PieChart(entries)
}