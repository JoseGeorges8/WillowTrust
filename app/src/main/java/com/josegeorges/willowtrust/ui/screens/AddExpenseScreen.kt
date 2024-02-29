package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.ui.composables.AddExpenseForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    onSaveButtonPressed: (Transaction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                title = { Text(text = stringResource(id = Screens.AddTransaction.title)) },
            )
        }
    ) { innerPadding ->
        AddExpenseForm(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(), onSaveButtonPressed = onSaveButtonPressed
        )
    }
}

@Preview
@Composable
fun AddExpenseFormPreview() {
    AddExpenseScreen {}
}