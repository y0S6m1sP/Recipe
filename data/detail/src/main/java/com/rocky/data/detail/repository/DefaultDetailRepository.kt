package com.rocky.data.detail.repository

import com.rocky.core.common.util.Async
import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.core.network.model.asRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultDetailRepository @Inject constructor(
    private val network: TheMealNetworkDataSource
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
}