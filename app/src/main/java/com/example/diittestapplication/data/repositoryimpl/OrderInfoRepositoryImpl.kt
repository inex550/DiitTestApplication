package com.example.diittestapplication.data.repositoryimpl

import com.example.diittestapplication.data.network.HoffApiService
import com.example.diittestapplication.domain.repository.OrderInfoRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderInfoRepositoryImpl(
    private val apiService: HoffApiService
): OrderInfoRepository {

    override fun getOrder(id: String) =
        apiService.loadOrderInfo(id)
            .subscribeOn(Schedulers.io())
}