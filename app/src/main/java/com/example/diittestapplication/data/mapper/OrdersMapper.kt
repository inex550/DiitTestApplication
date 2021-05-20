package com.example.diittestapplication.data.mapper

import com.example.diittestapplication.data.models.OrdersNet
import com.example.diittestapplication.domain.models.Order
import java.util.*

class OrdersMapper {

    fun map(item: OrdersNet) =
        item.items.map {
            it.run {
                Order(
                    date = Date(datetime * 1000L),
                    delivery = delivery,
                    status = status,
                    id = id,
                    number = number,
                    phone = phone,
                    sum = sum
                )
            }
        }
}