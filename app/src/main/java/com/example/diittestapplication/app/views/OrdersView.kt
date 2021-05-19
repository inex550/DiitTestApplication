package com.example.diittestapplication.app.views

import com.example.diittestapplication.models.Order

interface OrdersView: LoadingView {

    fun showOrders(orders: List<Order>)
}