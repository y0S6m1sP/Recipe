package com.rocky.recipe.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.rocky.feature.detail.DetailScreen
import com.rocky.feature.favorites.favoriteScreen
import com.rocky.feature.favorites.navigateToFavorites
import com.rocky.feature.home.HOME_ROUTE
import com.rocky.feature.home.homeScreen
import com.rocky.feature.home.navigateToHome
import com.rocky.feature.settings.navigateToSettings
import com.rocky.feature.settings.settingsScreen

@Composable
fun RecipeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navActions: RecipeNavigationActions = remember(navController) {
        RecipeNavigationActions(navController)
    }
) {
    var selectedNavItem by remember { mutableIntStateOf(0) }
    Scaffold(modifier = modifier) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = RecipeDestinations.LANDING_ROUTE,
            modifier = modifier,
            route = "main"
        ) {
            composable(RecipeDestinations.LANDING_ROUTE) {
                LandingNavGraph(navActions = navActions, selectedNavItem = selectedNavItem) {
                    selectedNavItem = it
                }
            }
            composable(RecipeDestinations.DETAIL_ROUTE) {
                DetailScreen(
                    paddingValues = paddingValues,
                    onBackPressed = { navController.popBackStack() })
            }
        }
    }
}

@Composable
fun LandingNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navActions: RecipeNavigationActions,
    selectedNavItem: Int,
    onSelectedItemChange: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            RecipeBottomBar(selectedNavItem = selectedNavItem) { index, destination ->
                onSelectedItemChange(index)
                navigateToLandingDestination(navController, destination)
            }
        },
    ) {
        NavHost(navController = navController, startDestination = HOME_ROUTE) {
            homeScreen(it) {
                navActions.navigateToDetail(it)
            }
            favoriteScreen(it) {
                navActions.navigateToDetail(it)
            }
            settingsScreen()
        }
    }

}

fun navigateToLandingDestination(
    navController: NavHostController,
    landingDestination: LandingDestination
) {
    val landingNavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }

    when (landingDestination) {
        LandingDestination.HOME -> navController.navigateToHome(landingNavOptions)
        LandingDestination.FAVORITES -> navController.navigateToFavorites(landingNavOptions)
        LandingDestination.SETTINGS -> navController.navigateToSettings(landingNavOptions)
    }
}