package com.josegeorges.willowtrust.di

import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSource
import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSourceImpl
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSource
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindTransactionDataSource(
        transactionDataSourceImpl: TransactionDataSourceImpl
    ): TransactionDataSource

    @Binds
    abstract fun bindBudgetDataSource(
        budgetDataSourceImpl: BudgetDataSourceImpl
    ): BudgetDataSource

}