package com.example.diittestapplication.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diittestapplication.R
import com.example.diittestapplication.presentation.App
import com.example.diittestapplication.presentation.screens.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragment_container_fl)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.INSTANCE.appComponent.inject(this)

        router.navigateTo(Screens.ordersScreen())
    }

    fun updateTitle(title: String) {
        supportActionBar?.title = title
    }
}