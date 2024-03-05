package com.josegeorges.willowtrust.data.repositories

import com.josegeorges.willowtrust.data.dataSources.expenses.ExpenseCategoryDataSource
import com.josegeorges.willowtrust.data.models.expenses.ExpenseCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExpenseCategoryRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val expenseCategoryDataSource: ExpenseCategoryDataSource
    ) {

    suspend fun getExpenseCategories() : List<ExpenseCategory> {
        return withContext(ioDispatcher) {
            return@withContext expenseCategoryDataSource.getExpenseCategories()
        }
    }

}