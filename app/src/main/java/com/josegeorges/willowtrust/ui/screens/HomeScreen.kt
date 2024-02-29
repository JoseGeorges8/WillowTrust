package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.transactions.previewBudgetWithDetails
import com.josegeorges.willowtrust.ui.composables.BudgetProgressBar
import com.josegeorges.willowtrust.ui.composables.TransactionList
import com.josegeorges.willowtrust.ui.viewmodels.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(uiState: StateFlow<HomeUiState>, onViewAllTransactionsButtonPressed: () -> Unit, onCreateBudgetButtonPressed: () -> Unit) {
    val state by uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                title = { Text(text = "Willow Trust") },
            )
        },
    ) { innerPadding ->
        when (state.selectedBudget) {
            null -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.no_budget_headline))
                    TextButton(onClick = onCreateBudgetButtonPressed) {
                        Text(stringResource(R.string.create_budget_button))
                    }
                }
            }
            else -> {
                Column(modifier = Modifier.padding(innerPadding)) {
                    BudgetProgressBar(state.selectedBudget!!)
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1.0F),
                            text = stringResource(id = R.string.transactions_list_title),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        TextButton(onClick = onViewAllTransactionsButtonPressed) {
                            Text(text = "View All")
                        }
                    }
                    HorizontalDivider()
                    TransactionList(transactions = state.selectedBudget!!.transactions, onTransactionLongPressed = {})
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreenWithoutBudget() {
    HomeScreen(
        MutableStateFlow(HomeUiState(selectedBudget = null)),
        onViewAllTransactionsButtonPressed = {},
        onCreateBudgetButtonPressed = {})
}

@Preview
@Composable
fun PreviewHomeScreenWithBudget() {
    HomeScreen(
        MutableStateFlow(HomeUiState(selectedBudget = previewBudgetWithDetails)),
        onViewAllTransactionsButtonPressed = {},
        onCreateBudgetButtonPressed = {})
}