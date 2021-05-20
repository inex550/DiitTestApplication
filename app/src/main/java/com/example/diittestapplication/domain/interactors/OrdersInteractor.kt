package com.example.diittestapplication.domain.interactors

import com.example.diittestapplication.domain.repository.OrdersRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class OrdersInteractor(
    private val repository: OrdersRepository
) {

    fun getOrders() = repository.getOrders()
        .observeOn(AndroidSchedulers.mainThread())
}