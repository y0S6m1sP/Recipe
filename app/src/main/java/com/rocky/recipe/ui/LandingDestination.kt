package com.rocky.recipe.ui

import com.rocky.recipe.R
import com.rocky.feature.favorites.R as favoritesR
import com.rocky.feature.home.R as homeR
import com.rocky.feature.settings.R as settingsR


enum class LandingDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextRes: Int,
) {
    HOME(
        selectedIcon = R.drawable.ic_filled_home,
        unselectedIcon = R.drawable.ic_outline_home,
        iconTextRes = homeR.string.feature_home_title
    ),
    FAVORITES(
        selectedIcon = R.drawable.ic_filled_favorite,
        unselectedIcon = R.drawable.ic_outline_favorite,
        iconTextRes = favoritesR.string.feature_favorites_title
    ),
    SETTINGS(
        selectedIcon = R.drawable.ic_filled_setting,
        unselectedIcon = R.drawable.ic_outline_setting,
        iconTextRes = settingsR.string.feature_settings_title
    )
}