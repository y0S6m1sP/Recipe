package com.rocky.core.network.di

import com.rocky.core.network.BuildConfig
import com.rocky.core.network.TheMealNetwork
import com.rocky.core.network.TheMealNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkhttpCallFactory(): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
            )
            .build()
    }


}

/**
 * move FlavoredNetworkModule to specific flavor package to binds different dataSource
 */
@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: TheMealNetwork): TheMealNetworkDataSource
}