package com.example.diittestapplication.domain.repository

import com.example.diittestapplication.domain.models.Order
import com.example.diittestapplication.domain.models.Orders
import io.reactivex.Single

interface OrdersRepository {

    fun getOrders(): Single<List<Order>>
}