package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.ui.composables.CreateBudgetForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBudgetScreen(onCreateButtonPressed: (Budget) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.create_budget_screen_title)) }
            )
        }
    ) { innerPadding ->
        CreateBudgetForm(modifier = Modifier.padding(innerPadding), onCreateButtonPressed = onCreateButtonPressed)
    }


}

@Preview
@Composable
fun PreviewCreateBudgetScreen() {
    CreateBudgetScreen {}
}