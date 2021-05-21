package com.example.diittestapplication.presentation.views

import com.arellomobile.mvp.MvpView
import com.example.diittestapplication.domain.models.Order

interface OrdersView: LoadingView, MvpView {

    fun showOrders(orders: List<Order>)
}