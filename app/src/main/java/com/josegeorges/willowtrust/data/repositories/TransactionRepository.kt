package com.josegeorges.willowtrust.data.repositories

import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSource
import java.time.LocalDate
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDataSource: TransactionDataSource,
) {

    suspend fun getTransactions() : List<Transaction> = transactionDataSource.getTransactions()
    suspend fun getTransactionsBetween(from: LocalDate, to: LocalDate) : List<Transaction> = transactionDataSource.getTransactionsBetween(from, to)
    suspend fun saveTransaction(transaction: Transaction) = transactionDataSource.saveTransaction(transaction)
    suspend fun deleteTransaction(transaction: Transaction) = transactionDataSource.deleteTransaction(transaction)

}