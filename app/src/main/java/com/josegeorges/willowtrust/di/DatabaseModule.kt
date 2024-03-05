package com.josegeorges.willowtrust.di

import android.content.Context
import androidx.room.Room
import com.josegeorges.willowtrust.data.db.AppDatabase
import com.josegeorges.willowtrust.data.db.daos.BudgetDao
import com.josegeorges.willowtrust.data.db.daos.TransactionCategoryDao
import com.josegeorges.willowtrust.data.db.daos.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideConnectorDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).createFromAsset("database/app.db")
        .build()

    @Provides
    fun provideTransactionDao(
        db: AppDatabase
    ): TransactionDao = db.transactionDao()

    @Provides
    fun provideBudgetDao(
        db: AppDatabase
    ): BudgetDao = db.budgetDao()

    @Provides
    fun provideTransactionCategoryDao(
        db: AppDatabase
    ): TransactionCategoryDao = db.transactionCategoryDao()


}