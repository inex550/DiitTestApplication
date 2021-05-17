package com.example.diittestapplication.app.views

import com.example.diittestapplication.models.Order

interface OrdersView {
    fun showLoading()

    fun hideLoading()

    fun showLoadingError(error: String)

    fun showOrders(orders: List<Order>)
}