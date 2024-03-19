package com.rocky.recipe.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rocky.feature.favorites.R as favoritesR
import com.rocky.feature.home.R as homeR
import com.rocky.feature.settings.R as settingsR


enum class LandingDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextRes: Int,
) {
    HOME(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.TwoTone.Home,
        iconTextRes = homeR.string.feature_home_title
    ),
    FAVORITES(
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.TwoTone.Favorite,
        iconTextRes = favoritesR.string.feature_favorites_title
    ),
    SETTINGS(
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.TwoTone.Settings,
        iconTextRes = settingsR.string.feature_settings_title
    )
}