package com.josegeorges.willowtrust.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.josegeorges.willowtrust.R
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.ui.composables.CreateBudgetForm
import com.josegeorges.willowtrust.ui.viewmodels.CreateBudgetViewModel

@Composable
fun CreateBudgetScreenRoute(navBackStackEntry: NavBackStackEntry, navController: NavController) {
    val viewModel: CreateBudgetViewModel = hiltViewModel<CreateBudgetViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateBudgetScreen(onCreateButtonPressed: (Budget) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.create_budget_screen_title)) }
            )
        }
    ) { innerPadding ->
        CreateBudgetForm(modifier = Modifier.padding(innerPadding), onCreateButtonPressed = onCreateButtonPressed)
    }


}

@Preview
@Composable
fun PreviewCreateBudgetScreen() {
    CreateBudgetScreen {}
}