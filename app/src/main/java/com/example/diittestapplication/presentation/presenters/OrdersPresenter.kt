package com.example.diittestapplication.presentation.presenters

import com.example.diittestapplication.presentation.views.OrdersView
import com.example.diittestapplication.domain.models.Orders
import com.example.diittestapplication.data.network.HoffApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class OrdersPresenter(
    private val ordersView: OrdersView
) {

    fun loadOrders() {
        ordersView.showLoading()

        HoffApi.apiService.loadOrders()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Orders>() {
                override fun onSuccess(orders: Orders) {
                    ordersView.hideLoading()
                    ordersView.showOrders(orders.items)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    ordersView.hideLoading()
                    ordersView.showLoadingError(e.message ?: "Unknown Error")
                }
            })
    }
}