package com.example.diittestapplication.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.diittestapplication.presentation.views.OrdersView
import com.example.diittestapplication.domain.interactors.OrdersInteractor
import javax.inject.Inject

@InjectViewState
class OrdersPresenter @Inject constructor(
    private val ordersInteractor: OrdersInteractor
): BasePresenter<OrdersView>() {

    fun loadOrders() {
        subscriptions.add(
            ordersInteractor.getOrders()
                .doOnSubscribe {
                    viewState.showLoading()
                }
                .doAfterTerminate {
                    viewState.hideLoading()
                }
                .doOnSuccess { orders ->
                    viewState.showOrders(orders)
                }
                .doOnError { e ->
                    e.printStackTrace()
                    viewState.showLoadingError(e.message ?: "Unknown Error")
                }
                .subscribe()
        )
    }
}