package com.example.diittestapplication.data.models

import com.example.diittestapplication.domain.models.Delivery
import com.example.diittestapplication.domain.models.Status
import java.util.*

data class OrderNet(
    val datetime: Long,
    val delivery: Delivery,
    val status: Status,
    val id: String,
    val number: String,
    val phone: String,
    val sum: Int
)