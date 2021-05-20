package com.example.diittestapplication.domain.interactors

import com.example.diittestapplication.domain.repository.OrderInfoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class OrderInfoInteractor @Inject constructor(
    private val repository: OrderInfoRepository
) {

    fun getOrderInfo(id: String) =
        repository.getOrder(id)
            .observeOn(AndroidSchedulers.mainThread())
}