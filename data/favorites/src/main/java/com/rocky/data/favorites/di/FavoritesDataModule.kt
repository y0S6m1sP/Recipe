package com.rocky.data.favorites.di

import com.rocky.data.favorites.repository.DefaultFavoritesRepository
import com.rocky.data.favorites.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesDataModule {

    @Binds
    internal abstract fun bindsFavoritesRepository(
        favoritesRepository: DefaultFavoritesRepository,
    ): FavoritesRepository
}