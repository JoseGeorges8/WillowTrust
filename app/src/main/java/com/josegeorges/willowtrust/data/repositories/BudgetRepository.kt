package com.josegeorges.willowtrust.data.repositories

import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BudgetRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val budgetDataSource: BudgetDataSource
) {
    suspend fun getBudgets(): List<Budget> {
        return withContext(ioDispatcher) {
            return@withContext budgetDataSource.getBudgets()
        }
    }
    // todo what happens if budget is not found?
    suspend fun getBudgetById(id: String): Budget {
        return withContext(ioDispatcher) {
            return@withContext budgetDataSource.getBudgetById(id)
        }
    }
    suspend fun saveBudget(budget: Budget) {
        withContext(ioDispatcher) {
            budgetDataSource.addBudget(budget)
        }
    }
    suspend fun deleteBudget(budget: Budget) {
        withContext(ioDispatcher) {
            budgetDataSource.deleteBudget(budget)
        }
    }
}