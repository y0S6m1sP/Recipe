package com.rocky.core.network

import com.rocky.core.model.Area
import com.rocky.core.model.Category
import com.rocky.core.model.Ingredients
import com.rocky.core.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealApi {

    @GET("search.php")
    suspend fun searchByName(@Query("s") query: String): List<Recipe>

    @GET("search.php")
    suspend fun listByFirstLetter(@Query("f") letter: String): List<Recipe>

    @GET("lookup.php")
    suspend fun lookupById(@Query("i") id: String): Recipe

    @GET("random.php")
    suspend fun random(): Recipe

    @GET("categories.php")
    suspend fun listCategories(): List<Category>

    @GET("list.php?c=list")
    suspend fun getCategoryList(): List<Category>

    @GET("list.php?a=list")
    suspend fun getAreaList(): List<Area>

    @GET("list.php?i=list")
    suspend fun getIngredientList(): List<Ingredients>

    @GET("filter.php")
    suspend fun filterByMainIngredient(@Query("i") ingredient: String): List<Recipe>

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): List<Recipe>

    @GET("filter.php")
    suspend fun filterByArea(@Query("a") area: String): List<Recipe>
}