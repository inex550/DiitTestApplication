package com.example.diittestapplication.presentation.presenters

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenter<V : MvpView>: MvpPresenter<V>() {

    val subscriptions = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}