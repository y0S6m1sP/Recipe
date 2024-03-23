package com.rocky.recipe.ui

import androidx.navigation.NavHostController
import com.rocky.recipe.ui.RecipeArgs.RECIPE_ID
import com.rocky.recipe.ui.RecipeScreens.DETAIL_SCREEN
import com.rocky.recipe.ui.RecipeScreens.LANDING_SCREEN

object RecipeScreens {
    const val LANDING_SCREEN = "landing"
    const val DETAIL_SCREEN = "detail"
}

object RecipeArgs {
    const val RECIPE_ID = "recipeId"
}

object RecipeDestinations {
    const val LANDING_ROUTE = LANDING_SCREEN
    const val DETAIL_ROUTE = "$DETAIL_SCREEN/{$RECIPE_ID}"
}

class RecipeNavigationActions(private val navController: NavHostController) {

    fun navigateToDetail(recipeId: String) {
        navController.navigate("$DETAIL_SCREEN/$recipeId")
    }
}