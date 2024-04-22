package com.rocky.feature.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rocky.core.model.Recipe
import com.rocky.core.ui.CollapsingToolBar
import com.rocky.core.ui.IngredientItem
import com.rocky.core.ui.IngredientLoadingItem
import com.rocky.core.ui.IngredientMultiplier
import com.rocky.core.ui.R
import com.rocky.core.ui.shimmerEffect

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onBackPressed: (() -> Unit),
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailContent(
        modifier = modifier,
        paddingValues = paddingValues,
        uiState = uiState,
        onBackPressed = onBackPressed,
        addFavorite = { viewModel.addFavorite(uiState.recipe) },
        deleteFavorite = { viewModel.deleteFavorite(uiState.recipe) }
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: DetailUiState,
    onBackPressed: (() -> Unit)? = null,
    addFavorite: (() -> Unit)? = null,
    deleteFavorite: (() -> Unit)? = null
) {

    var change by remember { mutableStateOf(false) }

    val favoriteSize by animateDpAsState(
        targetValue = if (change) 32.dp else 24.dp,
        animationSpec = tween(200),
        label = "favoriteSize"
    )

    if (favoriteSize == 32.dp) {
        change = false
    }

    CollapsingToolBar(
        modifier = modifier,
        paddingValues = paddingValues,
        imageUrl = uiState.recipe.strMealThumb,
        isLoading = uiState.isLoading,
        toolBarContent = {
            IconButton(onClick = { onBackPressed?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "navigationIcon",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable {
                        change = true
                        if (uiState.isFavorite) deleteFavorite?.invoke()
                        else addFavorite?.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(favoriteSize),
                    imageVector = if (uiState.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = "navigationIcon",
                    tint = if (uiState.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        content = {
            item {
                Text(
                    lineHeight = 40.sp,
                    text = uiState.recipe.strMeal,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
                Spacer(modifier = Modifier.height(16.dp))
                IngredientMultiplier()
            }
            items(uiState.recipe.getIngredients()) { ingredient ->
                IngredientItem(ingredient = ingredient)
            }
            item {
                Text(
                    text = stringResource(id = R.string.instructions),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = uiState.recipe.strInstructions,
                    fontSize = 16.sp
                )
            }
        },
        loadingContent = {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
            }
            items(3) {
                IngredientLoadingItem()
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    DetailContent(
        modifier = Modifier.fillMaxSize(),
        PaddingValues(16.dp),
        uiState = DetailUiState(
            false,
            Recipe(
                strMeal = "Pasta",
                strMealThumb = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
                strIngredient1 = "Sugar",
                strMeasure1 = "1 cup",
                strIngredient2 = "Water",
                strMeasure2 = "1 cup",
            )
        )
    )
}