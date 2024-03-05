package com.josegeorges.willowtrust.data.dataSources.expenses

import com.josegeorges.willowtrust.data.db.daos.TransactionCategoryDao
import com.josegeorges.willowtrust.data.models.expenses.ExpenseCategory
import javax.inject.Inject

class ExpenseCategoryDataSourceImpl @Inject constructor(
    private val expenseCategoryDao: TransactionCategoryDao
) : ExpenseCategoryDataSource {
    override suspend fun getExpenseCategories(): List<ExpenseCategory> {
        return expenseCategoryDao.getTransactionCategories().map { ExpenseCategory.fromEntity(it) }
    }
}

