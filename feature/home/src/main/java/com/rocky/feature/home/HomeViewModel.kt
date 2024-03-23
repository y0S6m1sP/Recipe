package com.rocky.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocky.core.model.Recipe
import com.rocky.data.repository.TheMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val theMealRepository: TheMealRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    fun searchByNames(name: String) {
        viewModelScope.launch {
            theMealRepository.searchByNames(name).collect {
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false, recipes = it)
                }
            }
        }
    }
}