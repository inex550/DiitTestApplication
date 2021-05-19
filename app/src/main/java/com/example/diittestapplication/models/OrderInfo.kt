package com.example.diittestapplication.models

data class OrderInfo(
        val address: String,
        val amount: Amount,
        val bonusCard: String,
        val deliveryTime: DeliveryTime?,
        val shop: Name,
        val payment: Name,
        val totalItemCount: Int,
        val items: List<OrderItem>,
        val services: List<Service>?
)