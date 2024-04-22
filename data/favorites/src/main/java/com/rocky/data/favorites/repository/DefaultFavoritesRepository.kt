package com.rocky.data.favorites.repository

import com.rocky.core.database.dao.RecipeDao
import com.rocky.core.database.model.asRecipe
import com.rocky.core.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultFavoritesRepository @Inject constructor(
    private val local: RecipeDao,
) : FavoritesRepository {

    override fun observeFavorites(): Flow<List<Recipe>> {
        return local.observeAll().map { list -> list.map { it.asRecipe() } }
    }
}