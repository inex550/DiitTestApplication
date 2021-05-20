package com.example.diittestapplication.presentation.presenters

import com.example.diittestapplication.presentation.views.OrderInfoView
import com.example.diittestapplication.domain.models.OrderInfo
import com.example.diittestapplication.data.network.HoffApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class OrderInfoPresenter(
        private val orderInfoView: OrderInfoView
) {
    init {
        orderInfoView.showLoading()
    }

    fun loadOrderInfo(orderId: String) {
        HoffApi.apiService.loadOrderInfo(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<OrderInfo>() {
                    override fun onSuccess(orderInfo: OrderInfo) {
                        orderInfoView.hideLoading()
                        showOrderInfo(orderInfo)
                    }

                    override fun onError(e: Throwable) {
                        orderInfoView.hideLoading()
                        orderInfoView.showLoadingError(e.message ?: "Unknown error")
                    }
                })


    }

    fun showOrderInfo(orderInfo: OrderInfo) {
        orderInfoView.showContent()

        orderInfoView.showTopOrderInfo()

        if (orderInfo.deliveryTime?.data != null)
            orderInfoView.showDeliveryTime(orderInfo.deliveryTime)

        orderInfoView.showAddress(orderInfo.address)

        orderInfoView.showOtherCenterInfo(orderInfo)

        orderInfoView.showOrderSum(orderInfo.amount)

        orderInfoView.showServices(orderInfo.services)

        orderInfoView.showOrderItems(orderInfo.items)
    }
}