package com.example.diittestapplication.utils

import com.example.diittestapplication.R
import java.text.SimpleDateFormat
import java.util.*

fun Int.format() =
        String.format("%,d", this).replace(',', ' ')

fun Int.pluralItemsCount(): String {
    if (this in 11..19)
        return "$this товаров"

    if (this % 10 == 1)
        return "$this товар"

    if (this % 10 in 2..4)
        return "$this товара"

    return "$this товаров"
}

fun Int.colorFromStatus() = when(this) {
    2 -> R.color.green
    7 -> R.color.green
    8 -> R.color.red
    else -> R.color.gray
}