package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.useCase.SearchUseCase
import com.example.dictionaryapp.viewmodel.base.BaseViewModel

class MainActivityViewModel(data: Any) : BaseViewModel() {
    var word: MutableLiveData<String> = MutableLiveData()
    var pronunciation: MutableLiveData<String> = MutableLiveData()

    var showDialog: MutableLiveData<Boolean> = MutableLiveData()
    var showNoConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showNoSuchWord: MutableLiveData<Boolean> = MutableLiveData()
    var searchResponse: MutableLiveData<SearchResponse> = MutableLiveData()

    private val navigationData: Parameter by lazy { data as Parameter }
    private val searchUseCase: SearchUseCase = SearchUseCase()

    init {
        searchUseCase.response.observeForever { newValue -> parseData(newValue)}
        searchUseCase.showDialog.observeForever { newValue -> showDialog.value = newValue }
        searchUseCase.showNoConnection.observeForever { newValue -> showNoConnection.value = newValue }
        searchUseCase.showNoSuchWord.observeForever { newValue -> showNoSuchWord.value = newValue }
    }

    fun searchCall(word: String?) {
        searchUseCase.execute(SearchUseCase.Parameter().apply {
            this.word = word
        })
    }

    private fun parseData(response: SearchResponse) {
        word.value = response.word
        pronunciation.value = response.pronunciation
        searchResponse.value = response
    }

    fun stopCall() {
        searchUseCase.stop()
    }

    class Parameter : BaseViewModel.Parameter()
}