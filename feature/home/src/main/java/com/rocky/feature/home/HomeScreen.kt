package com.rocky.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.rocky.core.ui.BigImageRecipeList
import com.rocky.core.ui.CategoryBar
import com.rocky.core.ui.SearchBar

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    onMealClick: ((String) -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.searchByNames("cake")
    }

    HomeContent(
        paddingValues = paddingValues,
        meals = uiState.recipes,
        onCategoryClick = { viewModel.searchByNames(it) },
        onSearch = { viewModel.searchByNames(it) },
        onMealClick = onMealClick
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    meals: List<Recipe>? = emptyList(),
    onCategoryClick: ((String) -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onMealClick: ((String) -> Unit)? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            lineHeight = 40.sp,
            text = stringResource(id = R.string.feature_home_slogan),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
        SearchBar(modifier = Modifier.padding(horizontal = 24.dp)) {
            onSearch?.invoke(it)
        }
        CategoryBar(modifier = Modifier.fillMaxWidth()) {
            onCategoryClick?.invoke(it)
        }
        BigImageRecipeList(
            modifier = Modifier.weight(1f),
            recipeList = meals ?: emptyList(),
            onMealClick = onMealClick
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeContentPreview() {
    val recipe = Recipe(strMeal = "Bruschettas with cheese", strMealThumb = "")
    val recipeList = listOf(recipe, recipe, recipe)
    HomeContent(paddingValues = PaddingValues(), meals = recipeList)
}


