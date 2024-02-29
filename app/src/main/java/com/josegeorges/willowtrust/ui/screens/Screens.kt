package com.josegeorges.willowtrust.ui.screens

import com.josegeorges.willowtrust.R

sealed class Screens(val route : String, val title: Int) {
    data object Home : Screens("home", R.string.home_screen_title)
    data object CreateBudget : Screens("create-budget", R.string.create_budget_screen_title)
    data object TransactionList : Screens("budgets/{budgetId}/transactions", R.string.transactions_screen_title)
    data object AddTransaction : Screens("budgets/{budgetId}/add-transaction", R.string.add_transaction_screen_title)
}