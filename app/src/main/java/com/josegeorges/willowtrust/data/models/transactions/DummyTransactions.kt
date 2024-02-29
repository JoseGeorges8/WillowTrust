package com.josegeorges.willowtrust.data.models.transactions

import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import java.time.LocalDate

val previewBudget = Budget(
    limit = 2000.0,
    name = "Preview Budget"
)

val previewTransactions = listOf(
    Transaction(
        institution = "Sake Sushi",
        amount = 500.0,
        date = LocalDate.parse("2024-02-11"),
        type = TransactionType.Income
    ),
    Transaction(
        institution = "Sake Sushi",
        amount = 100.0,
        date = LocalDate.parse("2024-02-11"),
        type = TransactionType.Income
    ),
    Transaction(
        institution = "Sake Sushi",
        amount = 100.0,
        date = LocalDate.parse("2024-02-11"),
        type = TransactionType.Income
    ),
    Transaction(
        institution = "Sake Sushi",
        amount = 100.0,
        date = LocalDate.parse("2024-02-11"),
        type = TransactionType.Income
    ),
    Transaction(
        institution = "Sake Sushi",
        amount = 100.0,
        date = LocalDate.parse("2024-02-11"),
        type = TransactionType.Income
    ),
)

val previewBudgetWithDetails = BudgetWithDetails(
    budget = previewBudget,
    transactions = previewTransactions
)