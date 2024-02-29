package com.josegeorges.willowtrust.data.models.transactions

import com.josegeorges.willowtrust.data.db.entities.TransactionEntity
import java.time.LocalDate
import java.util.UUID

open class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val institution: String,
    val amount: Double,
    val type: TransactionType,
    val date: LocalDate
) {

    fun toEntity(budgetId: String) : TransactionEntity = TransactionEntity(
        id = id,
        budgetId = budgetId,
        institution = institution,
        amount = amount,
        type = type.name,
        date = date
    )

    companion object {
        fun fromEntity(entity: TransactionEntity) : Transaction {
            return Transaction(
                id = entity.id,
                institution = entity.institution,
                amount = entity.amount,
                type = TransactionType.entries.first { entity.type == it.name},
                date = entity.date
            )
        }
    }
}

