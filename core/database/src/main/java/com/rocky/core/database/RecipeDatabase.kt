package com.rocky.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rocky.core.database.dao.RecipeDao
import com.rocky.core.database.model.LocalRecipe

@Database(entities = [LocalRecipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}