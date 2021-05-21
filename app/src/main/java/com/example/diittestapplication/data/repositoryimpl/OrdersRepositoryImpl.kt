package com.example.diittestapplication.data.repositoryimpl

import com.example.diittestapplication.data.mapper.OrdersMapper
import com.example.diittestapplication.data.network.HoffApiService
import com.example.diittestapplication.domain.models.Order
import com.example.diittestapplication.domain.repository.OrdersRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val apiService: HoffApiService,
    private val ordersMapper: OrdersMapper
): OrdersRepository {

    override fun getOrders(): Single<List<Order>> =
        apiService.loadOrders()
            .map {
                ordersMapper.map(it)
            }
            .subscribeOn(Schedulers.io())
}