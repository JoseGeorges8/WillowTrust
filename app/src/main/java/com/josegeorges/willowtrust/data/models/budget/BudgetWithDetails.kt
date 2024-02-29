package com.josegeorges.willowtrust.data.models.budget

import com.josegeorges.willowtrust.data.models.transactions.Transaction

data class BudgetWithDetails (
    val budget: Budget,
    val transactions: List<Transaction>
) {

    val currentTotalExpenses
        get() = transactions.sumOf { it.amount }

}