package com.example.diittestapplication.network

import com.example.diittestapplication.models.Order
import com.example.diittestapplication.models.Orders
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://hoff.ru/api/v2/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()


interface HoffApiService {
    @GET("order_list_ax?limit=20&page=1&device_id=8bba9f1d60af567d&isAndroid=true&isGooglePayEnabled=1&isSamsungPayEnabled=0&isAvailableSberPay=1&app_version=1.8.43&token=6c7ca481dbe77350a488c45f0615e5fd&location=19&xhoff=1018ce077ce6613911d3814c4eb6f464a781b045%3A3789")
    fun loadOrders(): Single<Orders>
}

object HoffApi {
    val apiService: HoffApiService by lazy {
        retrofit.create(HoffApiService::class.java)
    }
}