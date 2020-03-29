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

    private var disposable: CompositeDisposable? = null
    var searchResponse: MutableLiveData<List<SearchResponse>>
    var showDialog: MutableLiveData<Boolean>

    private val navigationData: Parameter by lazy {
        data as Parameter
    }

    init {
        disposable = CompositeDisposable()
        searchResponse = MutableLiveData()
        showDialog = MutableLiveData()
    }

    fun searchCall(word: String?) {
        val retrofit = ISearchApi.create(AppConstant.BASE_URL)
        disposable?.add(
            retrofit.meaning(word = word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { showDialog.value = true }
                .doOnTerminate { showDialog.value = false }
                .subscribe(
                    { value -> searchResponse.value = value },
                    { error -> Log.d("OnError", error.message) }
                )
        )
    }

    fun stopCall() {
        disposable?.dispose()
    }

    class Parameter : BaseViewModel.Parameter()
}