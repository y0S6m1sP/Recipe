package com.rocky.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocky.core.database.model.LocalRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg recipe: LocalRecipe)

    @Delete
    suspend fun delete(recipe: LocalRecipe)

    @Query("SELECT * FROM local_recipe WHERE idMeal = :id")
    fun observeById(id: String): Flow<LocalRecipe?>

    @Query("SELECt * FROM local_recipe")
    fun observeAll(): Flow<List<LocalRecipe>>
}