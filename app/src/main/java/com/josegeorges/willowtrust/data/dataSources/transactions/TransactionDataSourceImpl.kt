package com.josegeorges.willowtrust.data.dataSources.transactions

import com.josegeorges.willowtrust.data.db.daos.TransactionDao
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import javax.inject.Inject

class TransactionDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
) : TransactionDataSource {
    override suspend fun getTransactions(): List<Transaction> = transactionDao.getAll().map { Transaction.fromEntity(it) }
    override suspend fun getTransactionsForBudget(id: String): List<Transaction> =
        transactionDao.getTransactionsForBudget(id).map { Transaction.fromEntity(it) }

    override suspend fun saveTransaction(transaction: Transaction, budgetId: String) = transactionDao.insertAll(transaction.toEntity(budgetId))
    override suspend fun deleteTransaction(transaction: Transaction, budgetId: String) = transactionDao.delete(transaction.toEntity(budgetId))
}

