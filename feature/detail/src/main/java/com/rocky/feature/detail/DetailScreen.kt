package com.rocky.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.rocky.core.ui.HalfScreenImage
import com.rocky.core.ui.IngredientItem
import com.rocky.core.ui.IngredientMultiplier
import com.rocky.core.ui.R

@Composable
fun DetailScreen(modifier: Modifier = Modifier, viewModel: DetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailContent(
        modifier = modifier,
        recipe = uiState.recipe
    )
}

@Composable
fun DetailContent(modifier: Modifier = Modifier, recipe: Recipe) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        HalfScreenImage(model = recipe.strMealThumb, contentDescription = recipe.strMeal)
        LazyColumn(
            Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    DetailContent(
        modifier = Modifier.fillMaxSize(),
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