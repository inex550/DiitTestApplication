package com.example.diittestapplication.di

import com.example.diittestapplication.data.network.HoffApiService
import com.example.diittestapplication.data.repositoryimpl.OrderInfoRepositoryImpl
import com.example.diittestapplication.domain.repository.OrderInfoRepository
import dagger.Module
import dagger.Provides

@Module
class OrderInfoModule {

    @Provides
    fun provideOrderInfoRepository(apiService: HoffApiService): OrderInfoRepository =
        OrderInfoRepositoryImpl(apiService)
}