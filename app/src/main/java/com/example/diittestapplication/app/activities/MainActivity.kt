package com.example.diittestapplication.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diittestapplication.R
import com.example.diittestapplication.app.fragments.OrdersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_fl, OrdersFragment())
            .commit()
    }
}