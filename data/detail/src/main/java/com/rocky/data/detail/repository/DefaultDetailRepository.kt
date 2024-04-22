package com.rocky.data.detail.repository

import com.rocky.core.common.util.Async
import com.rocky.core.database.dao.RecipeDao
import com.rocky.core.database.model.asLocalRecipe
import com.rocky.core.database.model.asRecipe
import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.core.network.model.asRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultDetailRepository @Inject constructor(
    private val local: RecipeDao,
    private val network: TheMealNetworkDataSource,
) : DetailRepository {

    override fun lookupById(id: String): Flow<Async<Recipe>> {
        return flow {
            emit(Async.Loading)
            try {
                val response = network.lookupById(id)
                emit(Async.Success(response.meals?.firstOrNull()?.asRecipe() ?: Recipe()))
            } catch (e: Exception) {
                emit(Async.Error(0))
            }
        }
    }

    override suspend fun addFavorite(recipe: Recipe) {
        local.insertAll(recipe.asLocalRecipe())
    }

    override suspend fun deleteFavorite(recipe: Recipe) {
        local.delete(recipe.asLocalRecipe())
    }

    override fun observeFavorite(idMeal: String): Flow<Recipe?> {
        return local.observeById(idMeal).map { it?.asRecipe() }
    }
}