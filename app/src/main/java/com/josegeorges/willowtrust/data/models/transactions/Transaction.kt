package com.josegeorges.willowtrust.data.models.transactions

import com.josegeorges.willowtrust.data.db.daos.TransactionMappedEntity
import com.josegeorges.willowtrust.data.db.entities.TransactionEntity
import java.time.LocalDate
import java.util.UUID

open class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val institution: String,
    val amount: Double,
    val type: TransactionType,
    val date: LocalDate
) {

    fun toEntity() : TransactionEntity = TransactionEntity(
        id = id,
        categoryId = "categoryId", // todo
        institution = institution,
        amount = amount,
        type = type.name,
        date = date
    )

    companion object {
        fun fromEntity(entity: TransactionMappedEntity) : Transaction {
            return Transaction(
                id = entity.id,
                institution = entity.institution,
                amount = entity.amount,
                type = TransactionType.entries.first { entity.type == it.name},
                date = entity.date,
                category = entity.category
            )
        }
    }
}

