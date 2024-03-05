package com.josegeorges.willowtrust.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.josegeorges.willowtrust.data.db.entities.TransactionCategoryEntity

@Dao
interface TransactionCategoryDao {
    @Query("SELECT * FROM transaction_category")
    fun getTransactionCategories(): List<TransactionCategoryEntity>
}