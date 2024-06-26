package com.rocky.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocky.core.common.util.Async
import com.rocky.core.model.Recipe
import com.rocky.core.ui.categoryList
import com.rocky.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val recipes: List<Recipe> = emptyList(),
    val categoryIndex: Int = 0,
    val searchQuery: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        filterByCategory(0, categoryList.first())
    }

    fun searchByNames(name: String) {
        viewModelScope.launch {
            homeRepository.searchByNames(name).collect {
                _uiState.update { currentState ->
                    when (it) {
                        is Async.Error -> {
                            currentState.copy(isLoading = false)
                        }

                        Async.Loading -> {
                            currentState.copy(isLoading = true)
                        }

                        is Async.Success -> {
                            currentState.copy(
                                isLoading = false,
                                recipes = it.data,
                                searchQuery = name,
                                categoryIndex = -1
                            )
                        }
                    }
                }
            }
        }
    }

    fun filterByCategory(index: Int, category: String) {
        viewModelScope.launch {
            homeRepository.filterByCategory(category).collect {
                _uiState.update { currentState ->
                    when (it) {
                        is Async.Error -> {
                            currentState.copy(isLoading = false)
                        }

                        Async.Loading -> {
                            currentState.copy(isLoading = true)
                        }

                        is Async.Success -> {
                            currentState.copy(
                                isLoading = false,
                                recipes = it.data,
                                categoryIndex = index
                            )
                        }
                    }
                }
            }
        }
    }
}