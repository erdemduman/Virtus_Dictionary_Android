package com.example.dictionaryapp.api.usecase

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.api.service.ISearchApi
import com.example.dictionaryapp.api.usecase.base.BaseUseCase
import com.example.dictionaryapp.constant.AppConstant
import com.example.dictionaryapp.model.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchUseCase : BaseUseCase() {

    private val retrofit by lazy {
        ISearchApi.create(AppConstant.BASE_URL)
    }
    private val disposable: CompositeDisposable? = CompositeDisposable()

    var showDialog: MutableLiveData<Boolean> = MutableLiveData()
    var showNoConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showNoSuchWord: MutableLiveData<Boolean> = MutableLiveData()
    var showRequestTimeout: MutableLiveData<Boolean> = MutableLiveData()
    var response: MutableLiveData<List<SearchResponse>> = MutableLiveData()

    fun execute(parameter: Parameter) {
        var data = parameter

        disposable?.add(
            retrofit.meaning(word = data.word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { showDialog.value = true }
                .doOnTerminate { showDialog.value = false }
                .retry { count, throwable -> count < 3 && throwable is SocketTimeoutException }
                .subscribe(
                    { value -> response.value = value },
                    { error ->
                        if (error is UnknownHostException)
                            showNoConnection.value = true
                        else if (error is HttpException)
                            showNoSuchWord.value = true
                        else if (error is SocketTimeoutException)
                            showRequestTimeout.value = true
                    }
                )
        )
    }

    fun clear() {
        disposable?.clear()
    }

    fun stop() {
        disposable?.dispose()
    }

    class Parameter {
        var word: String? = null
    }

}
