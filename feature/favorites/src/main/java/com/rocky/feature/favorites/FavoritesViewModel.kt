package com.rocky.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocky.core.common.util.WhileUiSubscribed
import com.rocky.core.model.Recipe
import com.rocky.data.favorites.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class FavoritesUiState(
    val favoritesList: List<Recipe> = listOf()
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    favoritesRepository: FavoritesRepository
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> =
        favoritesRepository.observeFavorites().map {
            FavoritesUiState(favoritesList = it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = FavoritesUiState()
        )
}