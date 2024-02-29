package com.josegeorges.willowtrust.data.repositories

import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSource
import javax.inject.Inject

class BudgetRepository @Inject constructor(
    private val budgetDataSource: BudgetDataSource
) {
    suspend fun getBudgets(): List<Budget> = budgetDataSource.getBudgets()
    // todo what happens if budget is not found?
    suspend fun getBudgetById(id: String): Budget = budgetDataSource.getBudgetById(id)
    suspend fun saveBudget(budget: Budget) = budgetDataSource.addBudget(budget)
    suspend fun deleteBudget(budget: Budget) = budgetDataSource.deleteBudget(budget)
}