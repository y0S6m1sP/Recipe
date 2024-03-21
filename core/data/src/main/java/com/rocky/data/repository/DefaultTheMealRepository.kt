package com.rocky.data.repository

import com.rocky.core.model.Recipe
import com.rocky.core.network.TheMealNetworkDataSource
import com.rocky.data.model.asRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultTheMealRepository @Inject constructor(
    private val network: TheMealNetworkDataSource
) : TheMealRepository {
    override fun searchByNames(name: String): Flow<List<Recipe>> {
        return flow {
            emit(network.searchByName(name).meals?.map { it.asRecipe() } ?: emptyList())
        }
    }

}