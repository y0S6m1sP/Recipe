package com.rocky.data.detail.repository

import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.core.network.model.asRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultDetailRepository @Inject constructor(
    private val network: TheMealNetworkDataSource
) : DetailRepository {

    override fun lookupById(id: String): Flow<Recipe> {
        return flow {
            emit(network.lookupById(id).meals?.firstOrNull()?.asRecipe() ?: Recipe())
        }
    }
}