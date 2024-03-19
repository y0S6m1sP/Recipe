package com.rocky.feature.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val FAVORITES_ROUTE = "favorites_route"

fun NavController.navigateToFavorites(navOptions: NavOptions) =
    navigate(FAVORITES_ROUTE, navOptions)

fun NavGraphBuilder.favoriteScreen() {
    composable(route = FAVORITES_ROUTE) {
        FavoritesScreen()
    }
}