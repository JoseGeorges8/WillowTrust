package com.josegeorges.willowtrust.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val SELECTED_BUDGET_ID = stringPreferencesKey("selected_budget_id")

class HomeRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val dataStore: DataStore<Preferences>,
    private val budgetRepository: BudgetRepository,
    private val transactionRepository: TransactionRepository,
    ) {
    private val _selectedBudget = MutableStateFlow<BudgetWithDetails?>(null)
    val selectedBudget = _selectedBudget.asStateFlow()

    suspend fun getSelectedBudget(): BudgetWithDetails? {
        return withContext(ioDispatcher) {
            val preferences: Preferences = dataStore.data.first()
            val budgetId: String = preferences[SELECTED_BUDGET_ID] ?: return@withContext null
            val budget = budgetRepository.getBudgetById(budgetId)
            val transactions = transactionRepository.getTransactions()
            return@withContext BudgetWithDetails(budget = budget, transactions = transactions)
        }
    }

    suspend fun updateSelectedBudget(budgetId: String) {
        withContext(ioDispatcher) {
            dataStore.edit { preferences ->
                preferences[SELECTED_BUDGET_ID] = budgetId
            }
            _selectedBudget.value = getSelectedBudget()
        }
    }

}