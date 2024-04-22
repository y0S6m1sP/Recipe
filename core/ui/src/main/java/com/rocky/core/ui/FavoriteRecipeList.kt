package com.rocky.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rocky.core.model.Recipe

@Composable
fun FavoriteRecipeList(
    modifier: Modifier = Modifier,
    recipeList: List<Recipe>,
    onMealClick: ((String) -> Unit)? = null
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipeList) {
            FavoriteRecipeItem(recipe = it, onMealClick = onMealClick)
        }
    }
}

@Composable
private fun FavoriteRecipeItem(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onMealClick: ((String) -> Unit)? = null
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth(0.4f)
            .height(200.dp)
            .clickable {
                onMealClick?.invoke(recipe.idMeal)
            }
    ) {
        Box(contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = recipe.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
@Preview
private fun FavoriteRecipeListPreview() {
    val recipe = Recipe(strMeal = "Bruschettas with cheese", strMealThumb = "")
    val recipeList = listOf(recipe, recipe, recipe)
    FavoriteRecipeList(recipeList = recipeList)
}
