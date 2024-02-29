package com.josegeorges.willowtrust.data.dataSources.budget

import com.josegeorges.willowtrust.data.db.daos.BudgetDao
import com.josegeorges.willowtrust.data.models.budget.Budget
import javax.inject.Inject

class BudgetDataSourceImpl @Inject constructor(
    private val budgetDao: BudgetDao,
) : BudgetDataSource {

    override suspend fun getBudgets(): List<Budget> = budgetDao.getBudgets().map { Budget.fromEntity(it) }

    override suspend fun getBudgetById(id: String): Budget {
        val entity = budgetDao.getBudgetById(id)
        return Budget.fromEntity(entity)
    }

    override suspend fun addBudget(budget: Budget) = budgetDao.insertAll(budget.toEntity())

    override suspend fun deleteBudget(budget: Budget) = budgetDao.delete(budget.toEntity())
}

