package com.josegeorges.willowtrust.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "category_budget_limit",
    primaryKeys = ["category_id", "budget_id"],
    foreignKeys = [
        ForeignKey(
            entity = TransactionCategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = BudgetEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("budget_id"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class CategoryBudgetLimitEntity(
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "budget_id") val budgetId: String,
    @ColumnInfo(name = "amount") val amount: Double,
)
