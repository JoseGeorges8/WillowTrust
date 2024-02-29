package com.josegeorges.willowtrust.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val SELECTED_BUDGET_ID = stringPreferencesKey("selected_budget_id")

class HomeRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val budgetRepository: BudgetRepository,
    private val transactionRepository: TransactionRepository,
) {

    suspend fun getSelectedBudget(): BudgetWithDetails? {
        val preferences: Preferences = dataStore.data.first()
        val budgetId: String = preferences[SELECTED_BUDGET_ID] ?: return null
        val budget = budgetRepository.getBudgetById(budgetId)
        val transactions = transactionRepository.getTransactionsForBudget(budgetId)
        return BudgetWithDetails(budget = budget, transactions = transactions)
    }

    suspend fun updateSelectedBudget(budgetId: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_BUDGET_ID] = budgetId
        }
    }

}