package com.rocky.data.favorites.repository

import com.rocky.core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun observeFavorites(): Flow<List<Recipe>>
}