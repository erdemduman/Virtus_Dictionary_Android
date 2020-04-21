package com.virtusdictionary.app.api.useCase

import androidx.lifecycle.MutableLiveData
import com.virtusdictionary.app.api.service.ISearchApi
import com.virtusdictionary.app.api.useCase.base.BaseUseCase
import com.virtusdictionary.app.constant.AppConstant
import com.virtusdictionary.app.model.SearchResponse
import com.virtusdictionary.app.util.ApiException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import com.virtusdictionary.app.util.toApiException
import java.lang.Exception

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
        var word = parameter.word
        var callback = parameter.callback

        disposable?.add(
            retrofit.meaning(word = word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { callback?.onReady() }
                .doOnTerminate { callback?.onDone() }
                .retry { count, throwable -> count < 3 && throwable is SocketTimeoutException }
                .subscribe(
                    { value -> callback?.onSuccess(value) },
                    { error -> callback?.onFail(error.toApiException()) }
                )
        )
    }

    fun clear() {
        disposable?.clear()
    }

    fun stop() {
        disposable?.dispose()
    }

    class Parameter : BaseUseCase.Parameter<List<SearchResponse>, ApiException>() {
        var word: String? = null
    }
}
