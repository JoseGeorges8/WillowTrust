package com.josegeorges.willowtrust.data.dataSources.transactions

import com.josegeorges.willowtrust.data.models.transactions.Transaction
import java.time.LocalDate

interface TransactionDataSource {
    suspend fun getTransactions(): List<Transaction>
    suspend fun getTransactionsBetween(from: LocalDate, to: LocalDate): List<Transaction>
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}