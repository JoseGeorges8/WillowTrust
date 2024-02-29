package com.josegeorges.willowtrust.data.dataSources.transactions

import com.josegeorges.willowtrust.data.models.transactions.Transaction

interface TransactionDataSource {
    suspend fun getTransactions(): List<Transaction>
    suspend fun getTransactionsForBudget(id: String): List<Transaction>
    suspend fun saveTransaction(transaction: Transaction, budgetId: String)
    suspend fun deleteTransaction(transaction: Transaction, budgetId: String)
}