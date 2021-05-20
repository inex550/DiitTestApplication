package com.example.diittestapplication.presentation.views

import com.example.diittestapplication.domain.models.Order

interface OrdersView: LoadingView {

    fun showOrders(orders: List<Order>)
}