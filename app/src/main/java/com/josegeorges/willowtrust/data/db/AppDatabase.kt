package com.josegeorges.willowtrust.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josegeorges.willowtrust.data.db.converters.LocalDateConverter
import com.josegeorges.willowtrust.data.db.daos.BudgetDao
import com.josegeorges.willowtrust.data.db.daos.TransactionCategoryDao
import com.josegeorges.willowtrust.data.db.daos.TransactionDao
import com.josegeorges.willowtrust.data.db.entities.BudgetEntity
import com.josegeorges.willowtrust.data.db.entities.CategoryBudgetLimitEntity
import com.josegeorges.willowtrust.data.db.entities.TransactionCategoryEntity
import com.josegeorges.willowtrust.data.db.entities.TransactionEntity

@Database(entities = [BudgetEntity::class, TransactionEntity::class, TransactionCategoryEntity::class, CategoryBudgetLimitEntity::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "app_db"
    }

    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao
    abstract fun transactionCategoryDao(): TransactionCategoryDao
}