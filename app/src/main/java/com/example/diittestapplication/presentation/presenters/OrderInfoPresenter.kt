package com.example.diittestapplication.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.diittestapplication.presentation.views.OrderInfoView
import com.example.diittestapplication.domain.models.OrderInfo
import com.example.diittestapplication.domain.interactors.OrderInfoInteractor
import com.example.diittestapplication.presentation.App
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@InjectViewState
class OrderInfoPresenter @Inject constructor(
        private val interactor: OrderInfoInteractor
): BasePresenter<OrderInfoView>() {

    fun loadOrderInfo(orderId: String) {

        subscriptions.add(
            interactor.getOrderInfo(orderId)
                .doOnSubscribe { viewState.showLoading() }
                .doAfterTerminate { viewState.hideLoading() }
                .doOnSuccess { orderInfo ->
                    showOrderInfo(orderInfo)
                }
                .doOnError { e ->
                    viewState.showLoadingError(e.message ?: "Unknown error")
                }
                .subscribe()
        )
    }

    private fun showOrderInfo(orderInfo: OrderInfo) {
        viewState.showContent()

        viewState.showTopOrderInfo()

        if (orderInfo.deliveryTime?.data != null)
            viewState.showDeliveryTime(orderInfo.deliveryTime)

        viewState.showAddress(orderInfo.address)

        viewState.showOtherCenterInfo(orderInfo)

        viewState.showOrderSum(orderInfo.amount)

        viewState.showServices(orderInfo.services)

        viewState.showOrderItems(orderInfo.items)
    }
}