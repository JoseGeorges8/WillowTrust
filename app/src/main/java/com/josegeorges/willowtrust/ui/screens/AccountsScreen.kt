package com.josegeorges.willowtrust.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
@Composable
fun AccountsScreenRoute(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {
    AccountsScreen()
}

@Composable
private fun AccountsScreen() {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Preview
@Composable
fun PreviewAccountsScreen() {
    AccountsScreen()
}
