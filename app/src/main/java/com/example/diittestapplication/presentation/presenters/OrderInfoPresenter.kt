package com.example.diittestapplication.presentation.presenters

import com.example.diittestapplication.presentation.views.OrderInfoView
import com.example.diittestapplication.domain.models.OrderInfo
import com.example.diittestapplication.domain.interactors.OrderInfoInteractor
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class OrderInfoPresenter @Inject constructor(
    private val interactor: OrderInfoInteractor
) {
    lateinit var view: OrderInfoView

    fun loadOrderInfo(orderId: String) {
        view.showLoading()

        interactor.getOrderInfo(orderId)
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.hideLoading() }
            .subscribe(object : DisposableSingleObserver<OrderInfo>() {

                override fun onSuccess(orderInfo: OrderInfo) {
                    view.hideLoading()
                    showOrderInfo(orderInfo)
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                    view.showLoadingError(e.message ?: "Unknown error")
                }
            })
    }

    fun showOrderInfo(orderInfo: OrderInfo) {
        view.showContent()

        view.showTopOrderInfo()

        if (orderInfo.deliveryTime?.data != null)
            view.showDeliveryTime(orderInfo.deliveryTime)

        view.showAddress(orderInfo.address)

        view.showOtherCenterInfo(orderInfo)

        view.showOrderSum(orderInfo.amount)

        view.showServices(orderInfo.services)

        view.showOrderItems(orderInfo.items)
    }
}