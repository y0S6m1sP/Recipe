package com.rocky.recipe.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun RecipeApp() {
    // TODO: handle app state here
    RecipeNavGraph()
}

@Composable
fun RecipeBottomBar(
    destinations: List<LandingDestination> = createDestinationList(),
    onNavigate: (LandingDestination) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.Transparent,
    ) {
        destinations.forEachIndexed { index, destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = if (selectedItem == index) {
                            painterResource(id = destination.selectedIcon)
                        } else {
                            painterResource(id = destination.unselectedIcon)
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onNavigate(destination)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}

private fun createDestinationList(): List<LandingDestination> {
    return listOf(
        LandingDestination.HOME,
        LandingDestination.FAVORITES,
        LandingDestination.SETTINGS
    )
}