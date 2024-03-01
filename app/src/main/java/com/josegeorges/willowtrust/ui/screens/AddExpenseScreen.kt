package com.josegeorges.willowtrust.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.josegeorges.willowtrust.ui.composables.AddExpenseForm
import com.josegeorges.willowtrust.ui.viewmodels.AddTransactionViewModel

@Composable
fun AddExpenseScreenRoute(navBackStackEntry: NavBackStackEntry, navController: NavController) {
    val budgetId: String = navBackStackEntry.arguments?.getString("budgetId") ?: ""
    val viewModel = hiltViewModel<AddTransactionViewModel, AddTransactionViewModel.AddTransactionViewModelFactory> { factory ->
        factory.create(budgetId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val saved = uiState.transactionSaved
    if (saved) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
    }

    AddExpenseScreen {
        Log.d("WillowTrust", "Saving transaction: ${it.type.name}: ${it.institution} for $${it.amount}")
        viewModel.addTransaction(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddExpenseScreen(
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