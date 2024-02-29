package com.josegeorges.willowtrust.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.josegeorges.willowtrust.data.db.entities.BudgetEntity

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budget")
    fun getBudgets(): List<BudgetEntity>

    @Query("SELECT * FROM budget WHERE id = :id")
    fun getBudgetById(id: String): BudgetEntity

    @Insert
    fun insertAll(vararg budgets: BudgetEntity)

    @Delete
    fun delete(budgetEntity: BudgetEntity)
}