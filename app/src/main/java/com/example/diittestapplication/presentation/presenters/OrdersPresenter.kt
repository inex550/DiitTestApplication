package com.example.diittestapplication.presentation.presenters

import com.example.diittestapplication.presentation.views.OrdersView
import com.example.diittestapplication.domain.interactors.OrdersInteractor
import com.example.diittestapplication.domain.models.Order
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class OrdersPresenter @Inject constructor(
    private val ordersInteractor: OrdersInteractor
) {

    lateinit var view: OrdersView

    fun loadOrders() {
        view.showLoading()

        ordersInteractor.getOrders()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Order>>() {
                override fun onSuccess(orders: List<Order>) {
                    view.hideLoading()
                    view.showOrders(orders)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.hideLoading()
                    view.showLoadingError(e.message ?: "Unknown Error")
                }
            })
    }
}