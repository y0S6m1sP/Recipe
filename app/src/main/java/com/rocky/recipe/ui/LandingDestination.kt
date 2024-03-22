package com.rocky.recipe.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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