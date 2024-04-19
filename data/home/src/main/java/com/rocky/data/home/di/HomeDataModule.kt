package com.rocky.data.home.di

import com.rocky.data.repository.DefaultHomeRepository
import com.rocky.data.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModule {

    @Binds
    internal abstract fun bindsHomeRepository(
        homeRepository: DefaultHomeRepository,
    ): HomeRepository
}