package com.rocky.core.network

import com.rocky.core.model.Area
import com.rocky.core.model.Category
import com.rocky.core.model.Ingredients
import com.rocky.core.model.Recipe
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

class TheMealNetwork @Inject constructor(
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : TheMealNetworkDataSource {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TheMealApi::class.java)

    override suspend fun searchByName(query: String): List<Recipe> = api.searchByName(query = query)

    override suspend fun listByFirstLetter(letter: String): List<Recipe> =
        api.listByFirstLetter(letter = letter)

    override suspend fun lookupById(id: String): Recipe = api.lookupById(id = id)

    override suspend fun random(): Recipe = api.random()

    override suspend fun listCategories(): List<Category> = api.listCategories()

    override suspend fun getCategoryList(): List<Category> = api.getCategoryList()

    override suspend fun getAreaList(): List<Area> = api.getAreaList()

    override suspend fun getIngredientList(): List<Ingredients> = api.getIngredientList()

    override suspend fun filterByMainIngredient(ingredient: String): List<Recipe> =
        api.filterByMainIngredient(ingredient = ingredient)

    override suspend fun filterByCategory(category: String): List<Recipe> =
        api.filterByCategory(category = category)

    override suspend fun filterByArea(area: String): List<Recipe> = api.filterByArea(area = area)

}