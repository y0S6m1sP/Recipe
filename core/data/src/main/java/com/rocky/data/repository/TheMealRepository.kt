package com.rocky.data.repository

import com.rocky.core.model.Recipe
import com.rocky.data.util.Result
import kotlinx.coroutines.flow.Flow

interface TheMealRepository {

    fun searchByNames(name: String): Flow<Result<List<Recipe>>>

    fun lookupById(id: String): Flow<Recipe>
}