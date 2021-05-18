package com.example.diittestapplication.models

data class Order(
    val datetime: Long,
    val delivery: Delivery,
    val status: Status,
    val id: String,
    val number: String,
    val phone: String,
    val sum: Int
)