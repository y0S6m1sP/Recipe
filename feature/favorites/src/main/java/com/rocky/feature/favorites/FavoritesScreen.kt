package com.rocky.feature.favorites

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rocky.core.ui.FavoriteRecipeList
import com.rocky.core.ui.TitleIconText
import com.rocky.core.common.R as commonR

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onMealClick: ((String) -> Unit),
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    BackHandler(onBack = {})
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoritesContent(modifier = modifier, uiState = uiState, paddingValues, onMealClick)
}

@Composable
fun FavoritesContent(
    modifier: Modifier = Modifier,
    uiState: FavoritesUiState,
    paddingValues: PaddingValues,
    onMealClick: ((String) -> Unit)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        TitleIconText(
            modifier = Modifier.padding(24.dp),
            iconResId = commonR.drawable.ic_cooking,
            textResId = R.string.feature_favorites_title
        )
        FavoriteRecipeList(recipeList = uiState.favoritesList, onMealClick = onMealClick)
    }
}