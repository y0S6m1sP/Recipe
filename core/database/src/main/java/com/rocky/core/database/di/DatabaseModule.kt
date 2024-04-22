package com.rocky.core.database.di

import android.content.Context
import androidx.room.Room
import com.rocky.core.database.RecipeDatabase
import com.rocky.core.database.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "Recipe.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: RecipeDatabase): RecipeDao = database.recipeDao()
}
