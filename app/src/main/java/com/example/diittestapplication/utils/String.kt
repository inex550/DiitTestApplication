package com.example.diittestapplication.utils

fun String.dotBigString(limit: Int): String {
    if (length <= limit)
        return this

    return take(limit) + "..."
}