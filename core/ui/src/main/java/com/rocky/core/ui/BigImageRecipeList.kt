package com.rocky.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rocky.core.model.Recipe

@Composable
fun BigImageRecipeList(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    recipeList: List<Recipe>,
    onMealClick: ((String) -> Unit)? = null
) {

    if (isLoading) {
        BigImageRecipeCardLoading()
    } else {
        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipeList) {
                BigImageRecipeCard(recipe = it, onMealClick = onMealClick)
            }
        }
    }

}

@Composable
private fun BigImageRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onMealClick: ((String) -> Unit)? = null
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxHeight()
            .width(180.dp)
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
            Text(
                text = recipe.strMeal,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun BigImageRecipeCardLoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .shimmerEffect()
    )
}

@Composable
@Preview
private fun BigImageRecipeCardLoading() {
    Row {
        Spacer(modifier = Modifier.width(16.dp))
        repeat(2) {
            BigImageRecipeCardLoadingItem()
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
@Preview
private fun BigImageRecipeCardListPreview() {
    val recipe = Recipe(strMeal = "Bruschettas with cheese", strMealThumb = "")
    val recipeList = listOf(recipe, recipe, recipe)
    BigImageRecipeList(recipeList = recipeList)
}
