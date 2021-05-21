package com.example.diittestapplication.domain.models

import java.io.Serializable
import java.util.*

data class Order(
    val date: Date,
    val delivery: Delivery,
    val status: Status,
    val id: String,
    val number: String,
    val phone: String,
    val sum: Int
): Serializable