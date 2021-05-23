package com.example.diittestapplication.di

import com.example.diittestapplication.presentation.activities.MainActivity
import com.example.diittestapplication.presentation.fragments.OrderInfoFragment
import com.example.diittestapplication.presentation.fragments.OrdersFragment
import com.example.diittestapplication.presentation.presenters.OrderInfoPresenter
import com.example.diittestapplication.presentation.presenters.OrdersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        OrdersModule::class,
        OrderInfoModule::class,
        NetworkModule::class,
        NavigationModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(ordersFragment: OrdersFragment)

    fun inject(orderInfoFragment: OrderInfoFragment)
}