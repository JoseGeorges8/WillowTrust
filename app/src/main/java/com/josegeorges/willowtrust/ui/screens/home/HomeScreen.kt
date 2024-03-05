package com.josegeorges.willowtrust.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josegeorges.willowtrust.ui.screens.AccountsScreenRoute
import com.josegeorges.willowtrust.ui.screens.BudgetNavHost
import com.josegeorges.willowtrust.ui.screens.Screens
import com.josegeorges.willowtrust.ui.screens.SettingsScreenRoute

@Composable
fun HomeScreen(
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.bottomNavigationItems.first().route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screens.Account.route) { backStackEntry -> AccountsScreenRoute(backStackEntry, navController) }
            composable(Screens.Budget.route) { BudgetNavHost()}
        }
    }


}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}