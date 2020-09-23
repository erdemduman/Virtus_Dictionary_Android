package com.virtusdictionary.app.logic.useCase

import com.virtusdictionary.app.api.service.ISearchApi
import com.virtusdictionary.app.logic.useCase.base.BaseUseCase
import com.virtusdictionary.app.constant.AppConstant
import com.virtusdictionary.app.data.SearchResponse
import com.virtusdictionary.app.util.ApiException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import com.virtusdictionary.app.util.toApiException

class SearchUseCase : BaseUseCase() {

    private val retrofit by lazy {
        ISearchApi.create(AppConstant.BASE_URL)
    }
    private val disposable: CompositeDisposable? = CompositeDisposable()

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
