package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.repositories.BudgetRepository
import com.josegeorges.willowtrust.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateBudgetUiState(
    val budgetCreated: Boolean = false,
)

@HiltViewModel
class CreateBudgetViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val budgetRepository: BudgetRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(CreateBudgetUiState())
    val uiState: StateFlow<CreateBudgetUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun createBudget(budget: Budget) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetRepository.saveBudget(budget)
            val selectedBudget = homeRepository.getSelectedBudget()
            if(selectedBudget == null) homeRepository.updateSelectedBudget(budget.id)
            _uiState.update { currentState ->
                currentState.copy(
                    budgetCreated = true
                )
            }
        }
    }
}