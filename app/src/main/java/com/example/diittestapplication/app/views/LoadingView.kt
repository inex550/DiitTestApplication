package com.example.diittestapplication.app.views

interface LoadingView {

    fun showLoading()

    fun hideLoading()

    fun showLoadingError(error: String)
}