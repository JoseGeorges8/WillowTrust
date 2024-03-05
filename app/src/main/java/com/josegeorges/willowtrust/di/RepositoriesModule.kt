package com.josegeorges.willowtrust.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.josegeorges.willowtrust.data.dataSources.budget.BudgetDataSource
import com.josegeorges.willowtrust.data.dataSources.expenses.ExpenseCategoryDataSource
import com.josegeorges.willowtrust.data.dataSources.transactions.TransactionDataSource
import com.josegeorges.willowtrust.data.repositories.BudgetRepository
import com.josegeorges.willowtrust.data.repositories.ExpenseCategoryRepository
import com.josegeorges.willowtrust.data.repositories.HomeRepository
import com.josegeorges.willowtrust.data.repositories.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideTransactionRepository(
        transactionDataSource: TransactionDataSource
    ): TransactionRepository = TransactionRepository(transactionDataSource)

    @Provides
    fun provideExpenseCategoryRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        expenseCategoryDataSource: ExpenseCategoryDataSource
    ): ExpenseCategoryRepository = ExpenseCategoryRepository(ioDispatcher, expenseCategoryDataSource)

    @Provides
    fun provideBudgetRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        budgetDataSource: BudgetDataSource
    ): BudgetRepository = BudgetRepository(ioDispatcher, budgetDataSource)

    @Provides
    fun provideHomeRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        dataStore: DataStore<Preferences>,
        budgetRepository: BudgetRepository,
        transactionRepository: TransactionRepository
    ): HomeRepository = HomeRepository(ioDispatcher, dataStore, budgetRepository, transactionRepository)

}