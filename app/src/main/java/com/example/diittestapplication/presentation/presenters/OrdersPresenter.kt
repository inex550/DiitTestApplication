package com.example.diittestapplication.presentation.presenters

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.diittestapplication.presentation.views.OrdersView
import com.example.diittestapplication.domain.interactors.OrdersInteractor
import com.example.diittestapplication.presentation.App
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@InjectViewState
class OrdersPresenter: BasePresenter<OrdersView>() {

    @Inject
    lateinit var ordersInteractor: OrdersInteractor

    init {
        App.INSTANCE.appComponent.inject(this)
    }

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