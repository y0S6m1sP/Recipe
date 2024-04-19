package com.rocky.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    onNavigationIconClick: (() -> Unit),
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailContent(
        modifier = modifier,
        paddingValues = paddingValues,
        uiState = uiState,
        onNavigationIconClick = onNavigationIconClick
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: DetailUiState,
    onNavigationIconClick: (() -> Unit)? = null
) {

    CollapsingToolBar(
        modifier = modifier,
        paddingValues = paddingValues,
        imageUrl = uiState.recipe.strMealThumb,
        onNavigationIconClick = onNavigationIconClick,
        isLoading = uiState.isLoading,
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