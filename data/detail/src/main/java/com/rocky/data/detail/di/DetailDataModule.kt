package com.rocky.data.detail.di

import com.rocky.data.detail.repository.DefaultDetailRepository
import com.rocky.data.detail.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailDataModule {

    @Binds
    internal abstract fun bindsDetailRepository(
        detailRepository: DefaultDetailRepository,
    ): DetailRepository
}