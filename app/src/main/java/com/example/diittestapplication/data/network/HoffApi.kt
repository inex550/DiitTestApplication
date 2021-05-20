package com.example.diittestapplication.data.network

import com.example.diittestapplication.data.models.OrdersNet
import com.example.diittestapplication.domain.models.OrderInfo
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://hoff.ru/api/v2/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()


interface HoffApiService {
    @GET("order_list_ax?limit=20&page=1&device_id=8bba9f1d60af567d&isAndroid=true&isGooglePayEnabled=1&isSamsungPayEnabled=0&isAvailableSberPay=1&app_version=1.8.43&token=6c7ca481dbe77350a488c45f0615e5fd&location=19&xhoff=1018ce077ce6613911d3814c4eb6f464a781b045%3A3789")
    fun loadOrders(): Single<OrdersNet>

    @GET("order_info_ax?page=0&limit=20&device_id=8bba9f1d60af567d&isAndroid=true&isGooglePayEnabled=1&isSamsungPayEnabled=0&isAvailableSberPay=1&app_version=1.8.43&token=6c7ca481dbe77350a488c45f0615e5fd&location=19&xhoff=1ff0920a11e950e0e7c265f9bd35287e44558d23%3A8335")
    fun loadOrderInfo(@Query("order_id") orderId: String): Single<OrderInfo>
}

object HoffApi {
    val apiService: HoffApiService by lazy {
        retrofit.create(HoffApiService::class.java)
    }
}