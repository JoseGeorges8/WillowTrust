package com.josegeorges.willowtrust.data.models.budget

import com.josegeorges.willowtrust.data.db.entities.BudgetEntity
import java.util.UUID

data class Budget (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val limit: Double,
) {


    fun toEntity() : BudgetEntity = BudgetEntity(
        id = id,
        name = name,
        limit = limit,
    )

    override fun toString(): String {
        return name
    }
    companion object {
        fun fromEntity(entity: BudgetEntity) : Budget {
            return Budget(
                id = entity.id,
                name = entity.name,
                limit = entity.limit,
            )
        }
    }
}
