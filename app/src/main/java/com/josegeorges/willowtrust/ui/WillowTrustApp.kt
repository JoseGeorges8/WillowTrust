package com.josegeorges.willowtrust.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josegeorges.willowtrust.ui.screens.AddExpenseScreen
import com.josegeorges.willowtrust.ui.screens.CreateBudgetScreen
import com.josegeorges.willowtrust.ui.screens.HomeScreen
import com.josegeorges.willowtrust.ui.screens.Screens
import com.josegeorges.willowtrust.ui.screens.TransactionListScreen
import com.josegeorges.willowtrust.ui.viewmodels.AddTransactionViewModel
import com.josegeorges.willowtrust.ui.viewmodels.CreateBudgetViewModel
import com.josegeorges.willowtrust.ui.viewmodels.HomeViewModel
import com.josegeorges.willowtrust.ui.viewmodels.TransactionsScreenViewModel

@Composable
fun WillowTrustApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(Screens.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()

            LaunchedEffect(Unit) {
                viewModel.fetchSelectedBudget()
            }

            HomeScreen(viewModel.uiState, onViewAllTransactionsButtonPressed = {
                val selectedBudget = viewModel.uiState.value.selectedBudget
                if(selectedBudget != null) navController.navigate("budgets/${selectedBudget.budget.id}/transactions")
            }, onCreateBudgetButtonPressed = {
                navController.navigate(Screens.CreateBudget.route)
            })
        }
        composable(Screens.CreateBudget.route) {
            val viewModel: CreateBudgetViewModel = hiltViewModel<CreateBudgetViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val created = uiState.budgetCreated
            if (created) {
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }

            CreateBudgetScreen {
                Log.d("WillowTrust", "Creating budget: ${it.limit}")
                viewModel.createBudget(it)
            }
        }
        composable(Screens.TransactionList.route) { backStackEntry ->
            val budgetId: String = backStackEntry.arguments?.getString("budgetId") ?: ""
            val viewModel = hiltViewModel<TransactionsScreenViewModel, TransactionsScreenViewModel.TransactionsScreenViewModelFactory> { factory ->
                factory.create(budgetId)
            }
            LaunchedEffect(Unit) {
                viewModel.fetchLatestTransactions()
            }
            TransactionListScreen(viewModel.uiState, onTransactionLongPressed = {
                viewModel.deleteTransaction(it)
            }, onAddTransactionButtonPressed = {
                navController.navigate("budgets/${budgetId}/add-transaction")
            })
        }
        composable(Screens.AddTransaction.route) {backStackEntry ->
            val budgetId: String = backStackEntry.arguments?.getString("budgetId") ?: ""
            val viewModel = hiltViewModel<AddTransactionViewModel, AddTransactionViewModel.AddTransactionViewModelFactory> { factory ->
                factory.create(budgetId)
            }
            val uiState by viewModel.uiState.collectAsState()
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
    }
}