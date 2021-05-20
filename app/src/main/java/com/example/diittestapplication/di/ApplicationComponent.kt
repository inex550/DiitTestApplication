package com.example.diittestapplication.di

import com.example.diittestapplication.presentation.fragments.OrderInfoFragment
import com.example.diittestapplication.presentation.fragments.OrdersFragment
import dagger.Component

@Component(modules = [OrdersModule::class, OrderInfoModule::class])
interface ApplicationComponent {

    fun inject(ordersFragment: OrdersFragment)

    fun inject(orderInfoFragment: OrderInfoFragment)
}