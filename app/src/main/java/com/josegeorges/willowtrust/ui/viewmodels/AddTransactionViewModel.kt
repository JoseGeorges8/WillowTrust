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

data class AddTransactionUiState(
    val transactionSaved: Boolean = false,
)

@HiltViewModel(assistedFactory = AddTransactionViewModel.AddTransactionViewModelFactory::class)
class AddTransactionViewModel @AssistedInject constructor(
    @Assisted val budgetId: String,
    val savedStateHandle: SavedStateHandle,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    @AssistedFactory
    interface AddTransactionViewModelFactory {
        fun create(budgetId: String): AddTransactionViewModel
    }

    // Expose screen UI state
    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState: StateFlow<AddTransactionUiState> = _uiState.asStateFlow()

    // Handle business logic

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.saveTransaction(transaction, budgetId)
            _uiState.update { currentState ->
                currentState.copy(
                    transactionSaved = true
                )
            }
        }

    }
}