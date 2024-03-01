package com.josegeorges.willowtrust.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josegeorges.willowtrust.ui.screens.AddExpenseScreenRoute
import com.josegeorges.willowtrust.ui.screens.CreateBudgetScreenRoute
import com.josegeorges.willowtrust.ui.screens.HomeScreenRoute
import com.josegeorges.willowtrust.ui.screens.Screens
import com.josegeorges.willowtrust.ui.screens.TransactionListRoute

@Composable
fun WillowTrustApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(Screens.Home.route) { HomeScreenRoute(it, navController) }
        composable(Screens.CreateBudget.route) { CreateBudgetScreenRoute(it, navController) }
        composable(Screens.TransactionList.route) { TransactionListRoute(it, navController) }
        composable(Screens.AddTransaction.route) { AddExpenseScreenRoute(navBackStackEntry = it, navController = navController) }
    }
}