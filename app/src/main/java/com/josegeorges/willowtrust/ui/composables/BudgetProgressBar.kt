package com.josegeorges.willowtrust.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import com.josegeorges.willowtrust.data.models.transactions.previewBudgetWithDetails
import kotlinx.coroutines.delay

/** Iterate the progress value */
suspend fun loadProgress(totalProgress: Double, updateProgress: (Float) -> Unit) {
    for (i in 1..(100 * totalProgress).toInt()) {
        updateProgress(i.toFloat() / 100.toFloat())
        delay(10)
    }
}

@Composable
fun BudgetProgressBar(budgetWithDetails: BudgetWithDetails) {
    // Get the current expenses of the budget
    val totalProgress: Double = (budgetWithDetails.currentTotalExpenses / budgetWithDetails.budget.limit)
    var currentProgress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        loadProgress(totalProgress = totalProgress) { progress ->
            currentProgress = progress
        }
    }

    LinearProgressIndicator(
        progress = { currentProgress },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun PreviewBudgetProgressBar() {
    BudgetProgressBar(budgetWithDetails = previewBudgetWithDetails)
}