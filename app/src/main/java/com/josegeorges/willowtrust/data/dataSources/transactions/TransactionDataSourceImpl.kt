package com.josegeorges.willowtrust.data.dataSources.transactions

import com.josegeorges.willowtrust.data.db.daos.TransactionDao
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import java.time.LocalDate
import javax.inject.Inject

class TransactionDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
) : TransactionDataSource {
    override suspend fun getTransactions(): List<Transaction> = transactionDao.getAll().map { Transaction.fromEntity(it) }
    override suspend fun getTransactionsBetween(from: LocalDate, to: LocalDate): List<Transaction> =
        transactionDao.getAll().map { Transaction.fromEntity(it) }

    override suspend fun saveTransaction(transaction: Transaction) = transactionDao.insertAll(transaction.toEntity())
    override suspend fun deleteTransaction(transaction: Transaction) = transactionDao.delete(transaction.toEntity())
}

