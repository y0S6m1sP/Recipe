package com.rocky.data.repository

import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.data.model.asRecipe
import com.rocky.data.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultTheMealRepository @Inject constructor(
    private val network: TheMealNetworkDataSource
) : TheMealRepository {
    override fun searchByNames(name: String): Flow<Result<List<Recipe>>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = network.searchByName(name)
                emit(Result.Success(response.meals?.map { it.asRecipe() } ?: emptyList()))
            } catch (e: Exception) {
                emit(Result.Error(0))
            }
        }
    }

    override fun lookupById(id: String): Flow<Recipe> {
        return flow {
            emit(network.lookupById(id).meals?.firstOrNull()?.asRecipe() ?: Recipe())
        }
    }
}