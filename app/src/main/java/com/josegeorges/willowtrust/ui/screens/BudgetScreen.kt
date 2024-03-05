package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.models.transactions.previewBudgetWithDetails
import com.josegeorges.willowtrust.ui.composables.BudgetProgressBar
import com.josegeorges.willowtrust.ui.composables.FormDropdownField
import com.josegeorges.willowtrust.ui.composables.TransactionList
import com.josegeorges.willowtrust.ui.viewmodels.BudgetUiState
import com.josegeorges.willowtrust.ui.viewmodels.BudgetViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun BudgetNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.TransactionList.route,
    ) {
        composable(Screens.Budget.route) { BudgetScreenRoute(it, navController) }
        composable(Screens.CreateBudget.route) { CreateBudgetScreenRoute(it, navController) }
        composable(Screens.TransactionList.route) { TransactionListRoute(it, navController) }
        composable(Screens.AddTransaction.route) { AddExpenseScreenRoute(navBackStackEntry = it, navController = navController) }
    }
}

@Composable
fun BudgetScreenRoute(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {
    val viewModel: BudgetViewModel = hiltViewModel<BudgetViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchSelectedBudget()
        viewModel.fetchBudgets()
    }

    BudgetScreen(viewModel.uiState, onViewAllTransactionsButtonPressed = {
        val selectedBudget = viewModel.uiState.value.selectedBudget
        if (selectedBudget != null) navController.navigate("budgets/${selectedBudget.budget.id}/transactions")
    }, onCreateBudgetButtonPressed = {
        navController.navigate(Screens.CreateBudget.route)
    }, onNewBudgetSelected = {
        viewModel.updateSelectedBudget(it)
    })
}

@Composable
private fun BudgetScreen(
    uiState: StateFlow<BudgetUiState>,
    onViewAllTransactionsButtonPressed: () -> Unit,
    onCreateBudgetButtonPressed: () -> Unit,
    onNewBudgetSelected: (Budget) -> Unit
) {
    val state by uiState.collectAsStateWithLifecycle()
    Scaffold { innerPadding ->
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
                    FormDropdownField(
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(R.string.select_budget_dropdown_label),
                        value = state.selectedBudget!!.budget.name,
                        options = state.budgets,
                        onOptionSelected = onNewBudgetSelected,
                    )
                    Box(modifier = Modifier.padding(32.dp)) {
                        BudgetProgressBar(state.selectedBudget!!)
                    }
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
fun PreviewBudgetScreenWithoutBudget() {
    BudgetScreen(
        MutableStateFlow(BudgetUiState(selectedBudget = null)),
        onViewAllTransactionsButtonPressed = {},
        onCreateBudgetButtonPressed = {},
        onNewBudgetSelected = {}
    )
}

@Preview
@Composable
fun PreviewBudgetScreenWithBudget() {
    BudgetScreen(
        MutableStateFlow(BudgetUiState(selectedBudget = previewBudgetWithDetails)),
        onViewAllTransactionsButtonPressed = {},
        onCreateBudgetButtonPressed = {},
        onNewBudgetSelected = {}
    )
}
