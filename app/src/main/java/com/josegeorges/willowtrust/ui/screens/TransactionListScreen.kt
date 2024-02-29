package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.ui.composables.TransactionList
import com.josegeorges.willowtrust.ui.viewmodels.TransactionListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(uiState: StateFlow<TransactionListUiState>, onTransactionLongPressed: (transaction: Transaction) -> Unit, onAddTransactionButtonPressed: () -> Unit) {
    val state by uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                title = { Text(text = stringResource(id = Screens.TransactionList.title)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTransactionButtonPressed,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TransactionList(transactions = state.transactions, onTransactionLongPressed = { onTransactionLongPressed(it) })
        }
    }
}


@Preview
@Composable
fun TransactionListScreenPreview() {
    TransactionListScreen (MutableStateFlow(TransactionListUiState()), onTransactionLongPressed = {}, onAddTransactionButtonPressed = {})
}