package com.josegeorges.willowtrust.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.josegeorges.willowtrust.data.db.entities.TransactionEntity

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAll(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE budgetId=:budgetId")
    fun getTransactionsForBudget(budgetId: String): List<TransactionEntity>

    @Insert
    fun insertAll(vararg transactions: TransactionEntity)

    @Delete
    fun delete(transactionEntity: TransactionEntity)
}