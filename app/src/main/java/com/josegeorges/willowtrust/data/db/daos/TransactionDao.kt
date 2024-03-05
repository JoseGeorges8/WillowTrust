package com.josegeorges.willowtrust.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.josegeorges.willowtrust.data.db.entities.TransactionEntity
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.models.transactions.TransactionType
import java.time.LocalDate
import java.util.UUID

@Dao
interface TransactionDao {

    @Query(
        "SELECT transactions.id AS id, " +
                "transactions.institution AS institution, " +
                "transactions.type AS type, " +
                "transactions.amount AS amount, " +
                "transactions.date AS date, " +
                "transaction_category.name AS category " +
                "FROM transactions, transaction_category " +
                "WHERE transaction_category.id = transactions.category_id"
    )
    fun getAll(): List<TransactionMappedEntity>

    // todo create a query for querying transactions between two dates
//    @Query(
//        "SELECT transactions.id AS id, " +
//                "transactions.institution AS institution, " +
//                "transactions.type AS type, " +
//                "transactions.amount AS amount, " +
//                "transactions.date AS date, " +
//                "transaction_category.name AS category " +
//                "FROM transactions, transaction_category " +
//                "WHERE transaction_category.id = transactions.category_id"
//    )
//    fun getTransactionsBetween(from: LocalDate, to: LocalDate): List<TransactionMappedEntity>

    @Insert
    fun insertAll(vararg transactions: TransactionEntity)

    @Delete
    fun delete(transactionEntity: TransactionEntity)
}

data class TransactionMappedEntity(
    val id: String,
    val category: String,
    val institution: String,
    val amount: Double,
    val type: String,
    val date: LocalDate
)