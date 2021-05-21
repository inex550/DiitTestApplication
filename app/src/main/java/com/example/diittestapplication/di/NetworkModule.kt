package com.example.diittestapplication.di

import com.example.diittestapplication.data.network.HoffApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHoffRetrofitService(): HoffApiService =
        Retrofit.Builder()
            .baseUrl("https://hoff.ru/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HoffApiService::class.java)
}