package com.rocky.data.detail.repository

import com.rocky.core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun lookupById(id: String): Flow<Recipe>
}