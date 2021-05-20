package com.example.diittestapplication.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diittestapplication.R
import com.example.diittestapplication.presentation.App
import com.example.diittestapplication.presentation.screens.Screens
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragment_container_fl)

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.INSTANCE.router.navigateTo(Screens.ordersScreen())
    }

    fun updateTitle(title: String) {
        supportActionBar?.title = title
    }
}