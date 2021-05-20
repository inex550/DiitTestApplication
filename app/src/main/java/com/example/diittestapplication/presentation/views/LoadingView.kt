package com.example.diittestapplication.presentation.views

interface LoadingView {

    fun showLoading()

    fun hideLoading()

    fun showLoadingError(error: String)
}