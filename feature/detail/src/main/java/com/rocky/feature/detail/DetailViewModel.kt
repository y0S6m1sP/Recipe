package com.rocky.feature.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rocky.core.model.Recipe
import com.rocky.data.repository.TheMealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val StopTimeoutMillis: Long = 5000

val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)

data class DetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe = Recipe()
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val theMealRepository: TheMealRepository
) : ViewModel() {

    // maybe create a common module to store cross-feature constants
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])

    val uiState: StateFlow<DetailUiState> =
        theMealRepository.lookupById(recipeId)
            .map { recipe ->
                produceDetailUiState(recipe)
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = DetailUiState()
            )

    private fun produceDetailUiState(recipe: Recipe): DetailUiState = DetailUiState(recipe = recipe)

}