package com.rocky.data.repository

import com.rocky.core.common.util.Async
import com.rocky.core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun searchByNames(name: String): Flow<Async<List<Recipe>>>
    fun filterByCategory(category: String): Flow<Async<List<Recipe>>>
}