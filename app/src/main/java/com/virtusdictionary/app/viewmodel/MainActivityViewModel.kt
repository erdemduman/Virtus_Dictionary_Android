package com.virtusdictionary.app.viewmodel

import androidx.lifecycle.MutableLiveData
import com.virtusdictionary.app.api.useCase.SearchUseCase
import com.virtusdictionary.app.model.SearchResponse
import com.virtusdictionary.app.viewmodel.base.BaseViewModel

class MainActivityViewModel(data: Any) : BaseViewModel() {
    var searchResponse: MutableLiveData<List<SearchResponse>> = MutableLiveData()
    var showDialog: MutableLiveData<Boolean> = MutableLiveData()
    var showNoConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showNoSuchWord: MutableLiveData<Boolean> = MutableLiveData()
    var showRequestTimeout: MutableLiveData<Boolean> = MutableLiveData()

    private val navigationData: Parameter by lazy { data as Parameter }
    private val searchUseCase: SearchUseCase = SearchUseCase()

    init {
        searchUseCase.showDialog.observeForever { newValue -> showDialog.value = newValue }
        searchUseCase.response.observeForever { newValue -> searchResponse.value = newValue }
        searchUseCase.showNoConnection.observeForever { newValue -> showNoConnection.value = newValue }
        searchUseCase.showNoSuchWord.observeForever { newValue -> showNoSuchWord.value = newValue }
        searchUseCase.showRequestTimeout.observeForever { newValue -> showRequestTimeout.value = newValue }
    }

    fun searchCall(word: String?) {
        searchUseCase.execute(SearchUseCase.Parameter().apply {
            this.word = word
        })
    }

    fun clearCall() {
        searchUseCase.clear()
    }

    fun stopCall() {
        searchUseCase.stop()
    }

    class Parameter : BaseViewModel.Parameter()
}