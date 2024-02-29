package com.josegeorges.willowtrust.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josegeorges.willowtrust.data.models.budget.Budget
import com.josegeorges.willowtrust.data.models.budget.BudgetWithDetails
import com.josegeorges.willowtrust.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchSelectedBudget() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedBudget = homeRepository.getSelectedBudget()
            _uiState.update { currentState ->
                currentState.copy(
                    selectedBudget = selectedBudget
                )
            }
        }
    }

}