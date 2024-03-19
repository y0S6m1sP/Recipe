package com.rocky.recipe.ui

import androidx.navigation.NavHostController
import com.rocky.recipe.ui.RecipeScreens.LANDING_SCREEN

object RecipeScreens {
    const val LANDING_SCREEN = "landing"
}

object RecipeDestinations {
    const val LANDING_ROUTE = LANDING_SCREEN
}

class RecipeNavigationActions(private val navController: NavHostController) {

}