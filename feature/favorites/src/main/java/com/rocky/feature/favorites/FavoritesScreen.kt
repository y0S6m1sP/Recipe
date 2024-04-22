package com.rocky.feature.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rocky.core.ui.FavoriteRecipeList

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onMealClick: ((String) -> Unit),
    viewModel: FavoritesViewModel = hiltViewModel()
) {
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
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            text = stringResource(id = R.string.feature_favorites_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary
        )
        FavoriteRecipeList(recipeList = uiState.favoritesList, onMealClick = onMealClick)
    }
}