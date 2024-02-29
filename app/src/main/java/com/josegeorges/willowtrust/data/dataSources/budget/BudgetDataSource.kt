package com.josegeorges.willowtrust.data.dataSources.budget

import com.josegeorges.willowtrust.data.models.budget.Budget

interface BudgetDataSource {
    suspend fun getBudgets(): List<Budget>
    suspend fun getBudgetById(id: String): Budget
    suspend fun addBudget(budget: Budget)
    suspend fun deleteBudget(budget: Budget)
}