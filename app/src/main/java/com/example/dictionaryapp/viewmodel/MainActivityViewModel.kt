package com.example.dictionaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.api.service.ISearchApi
import com.example.dictionaryapp.api.usecase.SearchUseCase
import com.example.dictionaryapp.constant.AppConstant
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivityViewModel(data: Any) : BaseViewModel() {
    var searchResponse: MutableLiveData<List<SearchResponse>> = MutableLiveData()
    var showDialog: MutableLiveData<Boolean> = MutableLiveData()
    var showNoConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showNoSuchWord: MutableLiveData<Boolean> = MutableLiveData()

    private val navigationData: Parameter by lazy { data as Parameter }
    private val searchUseCase: SearchUseCase = SearchUseCase()

    init {
        searchUseCase.showDialog.observeForever { newValue -> showDialog.value = newValue }
        searchUseCase.response.observeForever { newValue -> searchResponse.value = newValue }
        searchUseCase.showNoConnection.observeForever { newValue -> showNoConnection.value = newValue }
        searchUseCase.showNoSuchWord.observeForever { newValue -> showNoSuchWord.value = newValue }
    }

    fun searchCall(word: String?) {
        searchUseCase.execute(SearchUseCase.Parameter().apply {
            this.word = word
        })
    }

    fun stopCall() {
        searchUseCase.stop()
    }

    class Parameter : BaseViewModel.Parameter()
}