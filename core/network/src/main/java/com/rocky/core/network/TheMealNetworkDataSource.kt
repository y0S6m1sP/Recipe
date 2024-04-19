package com.rocky.core.network

import com.rocky.core.model.Area
import com.rocky.core.model.Category
import com.rocky.core.model.Ingredients
import com.rocky.core.model.Recipe
import com.rocky.core.network.model.NetworkRecipes

interface TheMealNetworkDataSource {
    suspend fun searchByName(query: String): NetworkRecipes

    suspend fun listByFirstLetter(letter: String): List<Recipe>

    suspend fun lookupById(id: String): NetworkRecipes

    suspend fun random(): Recipe

    suspend fun listCategories(): List<Category>

    suspend fun getCategoryList(): List<Category>

    suspend fun getAreaList(): List<Area>

    suspend fun getIngredientList(): List<Ingredients>

    suspend fun filterByMainIngredient(ingredient: String): List<Recipe>

    suspend fun filterByCategory(category: String): NetworkRecipes

    suspend fun filterByArea(area: String): List<Recipe>
}