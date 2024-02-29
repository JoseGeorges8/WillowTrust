package com.josegeorges.willowtrust.data.models.transactions

enum class TransactionType {
    Expense,
    Income;

    override fun toString(): String {
        return name
    }
}