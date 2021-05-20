package com.example.diittestapplication.di

import com.example.diittestapplication.data.mapper.OrdersMapper
import com.example.diittestapplication.data.repositoryimpl.OrdersRepositoryImpl
import com.example.diittestapplication.domain.repository.OrdersRepository
import dagger.Module
import dagger.Provides

@Module
class OrdersModule {

    @Provides
    fun provideOrdersRepository(): OrdersRepository {
        val mapper = OrdersMapper()
        return OrdersRepositoryImpl(mapper)
    }
}