package com.example.diittestapplication.domain.repository

import com.example.diittestapplication.domain.models.Order
import io.reactivex.Single

interface OrdersRepository {

    fun getOrders(): Single<List<Order>>
}