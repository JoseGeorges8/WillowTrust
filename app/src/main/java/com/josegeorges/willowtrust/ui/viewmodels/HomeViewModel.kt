package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import com.josegeorges.willowtrust.data.repositories.BudgetRepository
import com.josegeorges.willowtrust.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val selectedBudget: BudgetWithDetails? = null,
    val budgets: List<Budget> = listOf(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val homeRepository: HomeRepository,
    private val budgetRepository: BudgetRepository,
) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            homeRepository.selectedBudget.collect {
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedBudget = it
                    )
                }
            }
        }
    }

    fun fetchSelectedBudget() {
        viewModelScope.launch {
            val selectedBudget = homeRepository.getSelectedBudget()
            _uiState.update { currentState ->
                currentState.copy(
                    selectedBudget = selectedBudget
                )
            }
        }
    }

    fun updateSelectedBudget(budget: Budget) {
        viewModelScope.launch {
            homeRepository.updateSelectedBudget(budget.id)
        }
    }

    fun fetchBudgets() {
        viewModelScope.launch {
            val budgets = budgetRepository.getBudgets()
            _uiState.update { currentState ->
                currentState.copy(
                    budgets = budgets
                )
            }
        }
    }

}