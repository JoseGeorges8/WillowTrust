package com.josegeorges.willowtrust.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSource
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSource
import com.josegeorges.willowtrust.data.repositories.BudgetRepository
import com.josegeorges.willowtrust.data.repositories.HomeRepository
import com.josegeorges.willowtrust.data.repositories.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideTransactionRepository(
        transactionDataSource: TransactionDataSource
    ): TransactionRepository = TransactionRepository(transactionDataSource)

    @Provides
    fun provideBudgetRepository(
        budgetDataSource: BudgetDataSource
    ): BudgetRepository = BudgetRepository(budgetDataSource)

    @Provides
    fun provideHomeRepository(
        dataStore: DataStore<Preferences>,
        budgetRepository: BudgetRepository,
        transactionRepository: TransactionRepository
    ): HomeRepository = HomeRepository(dataStore, budgetRepository, transactionRepository)

}