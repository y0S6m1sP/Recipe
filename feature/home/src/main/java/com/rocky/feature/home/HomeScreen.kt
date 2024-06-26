package com.rocky.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.rocky.core.ui.TitleIconText
import com.rocky.core.common.R as commonR

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    onMealClick: ((String) -> Unit)? = null
) {
    BackHandler(onBack = {})

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        paddingValues = paddingValues,
        uiState = uiState,
        onCategoryClick = { index, category -> viewModel.filterByCategory(index, category) },
        onSearch = { viewModel.searchByNames(it) },
        onMealClick = onMealClick
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: HomeUiState,
    onCategoryClick: ((Int, String) -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onMealClick: ((String) -> Unit)? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        Row {
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                TitleIconText(
                    iconResId = commonR.drawable.ic_cooking,
                    textResId = R.string.feature_home_title
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.feature_home_slogan),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                )
            }
        }
        SearchBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            searchQuery = uiState.searchQuery
        ) {
            onSearch?.invoke(it)
        }
        CategoryBar(
            modifier = Modifier.fillMaxWidth(),
            selectedCategoryItem = uiState.categoryIndex
        ) { index, category ->
            onCategoryClick?.invoke(index, category)
        }
        if (!uiState.isLoading && uiState.recipes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.feature_home_no_recipes),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            BigImageRecipeList(
                modifier = Modifier.weight(1f),
                isLoading = uiState.isLoading,
                recipeList = uiState.recipes,
                onMealClick = onMealClick
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeContentPreview() {
    val recipe = Recipe(strMeal = "Bruschettas with cheese", strMealThumb = "")
    val recipeList = listOf(recipe, recipe, recipe)
    HomeContent(paddingValues = PaddingValues(), uiState = HomeUiState(false, recipeList))
}


