package com.example.diittestapplication.presentation.screens

import android.os.Bundle
import com.example.diittestapplication.presentation.fragments.OrderInfoFragment
import com.example.diittestapplication.presentation.fragments.OrdersFragment
import com.example.diittestapplication.domain.models.Order
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ordersScreen() = FragmentScreen { OrdersFragment() }

    fun orderInfoScreen(bundle: Bundle) = FragmentScreen {
        OrderInfoFragment().apply {
            arguments = bundle
        }
    }
}