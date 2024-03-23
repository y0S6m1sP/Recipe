package com.rocky.data.repository

import com.rocky.core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface TheMealRepository {

    fun searchByNames(name: String): Flow<List<Recipe>>

    fun lookupById(id: String): Flow<Recipe>
}