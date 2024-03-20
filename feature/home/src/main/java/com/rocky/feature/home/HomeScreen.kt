package com.rocky.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rocky.core.model.Recipe

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.searchByNames("cake")
    }

    HomeContent(meals = uiState.meals)
}

@Composable
fun HomeContent(
    meals: List<Recipe> = emptyList(),
) {
    LazyColumn {
        items(meals) {
            RecipeItem(recipe = it)
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "${recipe.idMeal} ${recipe.strMeal}")
    }
}


