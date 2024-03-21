package com.rocky.recipe.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

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
    NavigationBar {
        destinations.forEachIndexed { index, destination ->
            NavigationBarItem(
                icon = {
                    if (selectedItem == index) {
                        Icon(imageVector = destination.selectedIcon, contentDescription = null)
                    } else {
                        Icon(imageVector = destination.unselectedIcon, contentDescription = null)
                    }
                },
                label = { Text(stringResource(id = destination.iconTextRes)) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onNavigate(destination)
                }
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