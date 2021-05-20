package com.example.diittestapplication.domain.repository

import com.example.diittestapplication.domain.models.OrderInfo
import io.reactivex.Single

interface OrderInfoRepository {

    fun getOrder(id: String): Single<OrderInfo>
}