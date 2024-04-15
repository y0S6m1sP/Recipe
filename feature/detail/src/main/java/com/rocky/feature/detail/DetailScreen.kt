package com.rocky.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.rocky.core.ui.IngredientMultiplier
import com.rocky.core.ui.R

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
        recipe = uiState.recipe,
        onNavigationIconClick = onNavigationIconClick
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    recipe: Recipe,
    onNavigationIconClick: (() -> Unit)? = null
) {

    CollapsingToolBar(
        modifier = modifier,
        paddingValues = paddingValues,
        imageUrl = recipe.strMealThumb,
        onNavigationIconClick = onNavigationIconClick
    ) {
        item {
            Column {
                Text(
                    lineHeight = 40.sp,
                    text = recipe.strMeal,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            IngredientMultiplier()
        }
        items(recipe.getIngredients()) { ingredient ->
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
                text = recipe.strInstructions,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    DetailContent(
        modifier = Modifier.fillMaxSize(),
        PaddingValues(16.dp),
        recipe = Recipe(
            strMeal = "Pasta",
            strMealThumb = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
            strIngredient1 = "Sugar",
            strMeasure1 = "1 cup",
            strIngredient2 = "Water",
            strMeasure2 = "1 cup",
        )
    )
}