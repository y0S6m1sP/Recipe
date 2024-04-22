package com.rocky.feature.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val FAVORITES_ROUTE = "favorites_route"

fun NavController.navigateToFavorites(navOptions: NavOptions) =
    navigate(FAVORITES_ROUTE, navOptions)

fun NavGraphBuilder.favoriteScreen(
    paddingValues: PaddingValues,
    onMealClick: ((String) -> Unit)
) {
    composable(route = FAVORITES_ROUTE) {
        FavoritesScreen(paddingValues = paddingValues, onMealClick = onMealClick)
    }
}