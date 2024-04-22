package com.rocky.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocky.core.common.di.IoDispatcher
import com.rocky.core.common.util.Async
import com.rocky.core.common.util.WhileUiSubscribed
import com.rocky.core.model.Recipe
import com.rocky.data.detail.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe = Recipe(),
    val isFavorite: Boolean = false
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailRepository: DetailRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // maybe create a common module to store cross-feature constants
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])

    val uiState: StateFlow<DetailUiState> =
        combine(
            detailRepository.observeFavorite(recipeId),
            detailRepository.lookupById(recipeId)
        ) { favorite, recipe ->
            when (recipe) {
                is Async.Loading -> DetailUiState(isLoading = true)
                is Async.Error -> DetailUiState()
                is Async.Success -> DetailUiState(
                    recipe = recipe.data,
                    isFavorite = favorite != null
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = DetailUiState()
        )

    fun addFavorite(recipe: Recipe) {
        viewModelScope.launch(dispatcher) {
            detailRepository.addFavorite(recipe)
        }
    }

    fun deleteFavorite(recipe: Recipe) {
        viewModelScope.launch(dispatcher) {
            detailRepository.deleteFavorite(recipe)
        }
    }

}