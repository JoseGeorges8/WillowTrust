package com.josegeorges.willowtrust.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = BudgetEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("budgetId"),
        onDelete = CASCADE
    )]
)
data class TransactionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "budgetId") val budgetId: String,
    @ColumnInfo(name = "institution") val institution: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "date") val date: LocalDate,
)