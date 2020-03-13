package com.example.dictionaryapp.viewmodel

import android.util.Log
import com.example.dictionaryapp.api.service.ISearchApi
import com.example.dictionaryapp.constant.AppConstant
import com.example.dictionaryapp.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(navigationData: Any) : BaseViewModel() {

    private var myCompositeDisposable: CompositeDisposable? = null

    private val navigationData: Parameter by lazy {
        navigationData as Parameter
    }

    init {
        myCompositeDisposable = CompositeDisposable()
    }

    fun retroCall() {
        val retrofit = ISearchApi.create(AppConstant.BASE_URL)
        myCompositeDisposable?.add(
            retrofit.meaning(word = "run")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> Log.d("OnNormal", "Entered") },
                    { error -> Log.d("OnError", "Entered") }
                )
        )
    }

    class Parameter : BaseViewModel.Parameter() {

    }
}