package com.example.dictionaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.api.service.ISearchApi
import com.example.dictionaryapp.constant.AppConstant
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(data: Any) : BaseViewModel() {

    private var myCompositeDisposable: CompositeDisposable? = null
    lateinit var searchResponse: MutableLiveData<List<SearchResponse>>

    private val navigationData: Parameter by lazy {
        data as Parameter
    }

    init {
        myCompositeDisposable = CompositeDisposable()
        searchResponse = MutableLiveData()
    }

    fun retroCall() {
        val retrofit = ISearchApi.create(AppConstant.BASE_URL)
        myCompositeDisposable?.add(
            retrofit.meaning(word = "mean")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->  searchResponse.value = value},
                    { error -> Log.d("OnError", error.message) }
                )
        )
    }

    class Parameter : BaseViewModel.Parameter()
}