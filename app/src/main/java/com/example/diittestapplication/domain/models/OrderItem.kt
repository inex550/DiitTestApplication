package com.example.diittestapplication.domain.models

data class OrderItem(
        val articul: String,
        val imageUrl: String,
        val name: String,
        val price: Int,
        val quantity: Int
)