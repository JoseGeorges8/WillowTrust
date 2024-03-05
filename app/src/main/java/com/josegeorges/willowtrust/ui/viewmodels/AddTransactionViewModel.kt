package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.expenses.ExpenseCategory
import com.josegeorges.willowtrust.data.models.transactions.Transaction
import com.josegeorges.willowtrust.data.repositories.ExpenseCategoryRepository
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
import javax.inject.Inject

data class AddTransactionUiState(
    val expenseCategories: List<ExpenseCategory> = listOf(),
    val transactionSaved: Boolean = false,
)

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val transactionRepository: TransactionRepository,
    private val expenseCategoryRepository: ExpenseCategoryRepository
) : ViewModel() {

    init {
        fetchExpenseCategories()
    }

    // Expose screen UI state
    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState: StateFlow<AddTransactionUiState> = _uiState.asStateFlow()

    // Handle business logic
    private fun fetchExpenseCategories() {
        viewModelScope.launch {
            val categories = expenseCategoryRepository.getExpenseCategories()
            _uiState.update { currentState ->
                currentState.copy(
                    expenseCategories = categories
                )
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.saveTransaction(transaction)
            _uiState.update { currentState ->
                currentState.copy(
                    transactionSaved = true
                )
            }
        }

    }
}