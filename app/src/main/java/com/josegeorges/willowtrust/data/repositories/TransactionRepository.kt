package com.josegeorges.willowtrust.data.repositories

import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSource
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDataSource: TransactionDataSource,
) {

    suspend fun getTransactions() : List<Transaction> = transactionDataSource.getTransactions()
    suspend fun getTransactionsForBudget(budgetId: String) : List<Transaction> = transactionDataSource.getTransactionsForBudget(budgetId)
    suspend fun saveTransaction(transaction: Transaction, budgetId: String) = transactionDataSource.saveTransaction(transaction, budgetId)
    suspend fun deleteTransaction(transaction: Transaction, budgetId: String) = transactionDataSource.deleteTransaction(transaction, budgetId)

}