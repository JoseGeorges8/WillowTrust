package com.josegeorges.willowtrust.data.models.expenses

import com.josegeorges.willowtrust.data.db.entities.TransactionCategoryEntity
import java.util.UUID

data class ExpenseCategory (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String = "",
) {
    fun toEntity() : TransactionCategoryEntity = TransactionCategoryEntity(
        id = id,
        name = name,
        description = description,
    )
    override fun toString(): String {
        return name
    }

    companion object {
        fun fromEntity(entity: TransactionCategoryEntity) : ExpenseCategory {
            return ExpenseCategory(
                id = entity.id,
                name = entity.name,
                description = entity.description,
            )
        }
    }
}