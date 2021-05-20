package com.example.diittestapplication.data.repositoryimpl

import com.example.diittestapplication.data.network.HoffApi
import com.example.diittestapplication.domain.repository.OrderInfoRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderInfoRepositoryImpl: OrderInfoRepository {

    override fun getOrder(id: String) =
        HoffApi.apiService.loadOrderInfo(id)
            .subscribeOn(Schedulers.io())
}