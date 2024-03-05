package com.josegeorges.willowtrust.data.dataSources.expenses

import com.josegeorges.willowtrust.data.models.expenses.ExpenseCategory

interface ExpenseCategoryDataSource {
    suspend fun getExpenseCategories(): List<ExpenseCategory>
}