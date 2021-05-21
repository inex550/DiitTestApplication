package com.example.diittestapplication.presentation

import android.app.Application
import com.example.diittestapplication.di.ApplicationComponent
import com.example.diittestapplication.di.DaggerApplicationComponent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}