package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.repositories.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TransactionListUiState(
    val transactions: List<Transaction> = listOf(),
)

@HiltViewModel
class TransactionsScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val transactionRepository: TransactionRepository
) : ViewModel() {


    // Expose screen UI state
    private val _uiState = MutableStateFlow(TransactionListUiState())
    val uiState: StateFlow<TransactionListUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun fetchLatestTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactions = transactionRepository.getTransactions()
            _uiState.update { currentState ->
                currentState.copy(
                    transactions = transactions
                )
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.deleteTransaction(transaction)
            val transactions = mutableListOf<Transaction>().also { it -> it.addAll(uiState.value.transactions.filter { it.id != transaction.id }) }
            _uiState.update { currentState ->
                currentState.copy(
                    transactions = transactions
                )
            }
        }
    }

}