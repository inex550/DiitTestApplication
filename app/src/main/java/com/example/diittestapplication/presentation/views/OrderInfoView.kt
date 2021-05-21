package com.example.diittestapplication.presentation.views

import com.arellomobile.mvp.MvpView
import com.example.diittestapplication.domain.models.*

interface OrderInfoView: LoadingView, MvpView {

    fun showContent()

    fun showTopOrderInfo()

    fun showDeliveryTime(deliveryTime: DeliveryTime)

    fun showAddress(address: String)

    fun showOtherCenterInfo(orderInfo: OrderInfo)

    fun showOrderSum(amount: Amount)

    fun showServices(services: List<Service>?)

    fun showOrderItems(items: List<OrderItem>)
}