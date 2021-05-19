package com.example.diittestapplication.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.timeToFormatedString(): String =
        SimpleDateFormat("от dd MMMM yyyy, hh:mm", Locale("ru"))
                .format(Date(this * 1000L))