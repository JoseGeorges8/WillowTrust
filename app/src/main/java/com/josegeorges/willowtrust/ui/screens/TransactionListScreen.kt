package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.ui.composables.TransactionList
import com.josegeorges.willowtrust.ui.viewmodels.TransactionListUiState
import com.josegeorges.willowtrust.ui.viewmodels.TransactionsScreenViewModel

@Composable
fun TransactionListRoute(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {
    val viewModel = hiltViewModel<TransactionsScreenViewModel>()
    val uiState: TransactionListUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchLatestTransactions()
    }
    TransactionListScreen(uiState, onTransactionLongPressed = {
        viewModel.deleteTransaction(it)
    }, onAddTransactionButtonPressed = {
        navController.navigate(Screens.AddTransaction.route)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransactionListScreen(uiState: TransactionListUiState, onTransactionLongPressed: (transaction: Transaction) -> Unit, onAddTransactionButtonPressed: () -> Unit) {
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
            TransactionList(transactions = uiState.transactions, onTransactionLongPressed = { onTransactionLongPressed(it) })
        }
    }
}


@Preview
@Composable
fun TransactionListScreenPreview() {
    TransactionListScreen (TransactionListUiState(), onTransactionLongPressed = {}, onAddTransactionButtonPressed = {})
}