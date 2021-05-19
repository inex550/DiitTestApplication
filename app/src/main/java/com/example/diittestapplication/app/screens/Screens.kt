package com.example.diittestapplication.app.screens

import com.example.diittestapplication.app.fragments.OrderInfoFragment
import com.example.diittestapplication.app.fragments.OrdersFragment
import com.example.diittestapplication.models.Order
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ordersScreen() = FragmentScreen { OrdersFragment() }
    fun orderInfoScreen(order: Order) = FragmentScreen { OrderInfoFragment(order) }
}