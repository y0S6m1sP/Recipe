package com.rocky.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocky.core.common.util.Async
import com.rocky.core.model.Recipe
import com.rocky.data.detail.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
    detailRepository: DetailRepository
) : ViewModel() {

    // maybe create a common module to store cross-feature constants
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])

    val uiState: StateFlow<DetailUiState> =
        detailRepository.lookupById(recipeId)
            .map { recipe ->
                when (recipe) {
                    is Async.Loading -> DetailUiState(isLoading = true)
                    is Async.Error -> DetailUiState()
                    is Async.Success -> produceDetailUiState(recipe.data)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = DetailUiState()
            )

    private fun produceDetailUiState(recipe: Recipe): DetailUiState = DetailUiState(recipe = recipe)

}