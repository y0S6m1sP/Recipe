package com.rocky.data.di

import com.rocky.data.repository.DefaultTheMealRepository
import com.rocky.data.repository.TheMealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsTheMealRepository(
        theMealRepository: DefaultTheMealRepository,
    ): TheMealRepository
}