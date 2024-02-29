package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.repositories.TransactionRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TransactionListUiState(
    val transactions: List<Transaction> = listOf(),
)

@HiltViewModel(assistedFactory = TransactionsScreenViewModel.TransactionsScreenViewModelFactory::class)
class TransactionsScreenViewModel @AssistedInject constructor(
    @Assisted val budgetId: String,
    val savedStateHandle: SavedStateHandle,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    @AssistedFactory
    interface TransactionsScreenViewModelFactory {
        fun create(budgetId: String): TransactionsScreenViewModel
    }

    // Expose screen UI state
    private val _uiState = MutableStateFlow(TransactionListUiState())
    val uiState: StateFlow<TransactionListUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun fetchLatestTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactions = transactionRepository.getTransactionsForBudget(budgetId)
            _uiState.update { currentState ->
                currentState.copy(
                    transactions = transactions
                )
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.deleteTransaction(transaction, budgetId)
            val transactions = mutableListOf<Transaction>().also { it -> it.addAll(uiState.value.transactions.filter { it.id != transaction.id }) }
            _uiState.update { currentState ->
                currentState.copy(
                    transactions = transactions
                )
            }
        }
    }

}