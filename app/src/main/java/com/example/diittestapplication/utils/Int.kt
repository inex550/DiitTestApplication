package com.example.diittestapplication.utils

import com.example.diittestapplication.R
import java.text.SimpleDateFormat
import java.util.*

fun Int.format() =
        String.format("%,d", this).replace(',', ' ')


fun Int.colorFromStatus() = when(this) {
    2 -> R.color.green
    7 -> R.color.green
    8 -> R.color.red
    else -> R.color.gray
}