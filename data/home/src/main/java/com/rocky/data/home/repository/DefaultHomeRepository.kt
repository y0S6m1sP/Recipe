package com.rocky.data.repository

import com.rocky.core.common.util.Async
import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.core.network.model.asRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val network: TheMealNetworkDataSource
) : HomeRepository {
    override fun searchByNames(name: String): Flow<Async<List<Recipe>>> {
        return flow {
            emit(Async.Loading)
            try {
                val response = network.searchByName(name)
                emit(Async.Success(response.meals?.map { it.asRecipe() } ?: emptyList()))
            } catch (e: Exception) {
                emit(Async.Error(0))
            }
        }
    }

    override fun filterByCategory(category: String): Flow<Async<List<Recipe>>> {
        return flow {
            emit(Async.Loading)
            try {
                val response = network.filterByCategory(category)
                emit(Async.Success(response.meals?.map { it.asRecipe() } ?: emptyList()))
            } catch (e: Exception) {
                emit(Async.Error(0))
            }
        }
    }
}