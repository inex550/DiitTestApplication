package com.example.diittestapplication.presentation

import android.app.Application
import com.example.diittestapplication.di.DaggerApplicationComponent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val appComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}