package com.example.diittestapplication.di

import com.example.diittestapplication.data.repositoryimpl.OrdersRepositoryImpl
import com.example.diittestapplication.domain.repository.OrdersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class OrdersModule {

    @Binds
    abstract fun provideOrdersRepository(repositoryImpl: OrdersRepositoryImpl): OrdersRepository
}