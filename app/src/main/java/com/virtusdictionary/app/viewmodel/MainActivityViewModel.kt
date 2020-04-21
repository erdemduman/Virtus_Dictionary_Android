package com.virtusdictionary.app.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.virtusdictionary.app.R
import com.virtusdictionary.app.api.useCase.SearchUseCase
import com.virtusdictionary.app.model.SearchResponse
import com.virtusdictionary.app.ui.popup.LoadingDialog
import com.virtusdictionary.app.ui.popup.ToastDialog
import com.virtusdictionary.app.util.ApiException
import com.virtusdictionary.app.viewmodel.base.BaseViewModel

class MainActivityViewModel(data: Any) : BaseViewModel() {
    var searchResponse: MutableLiveData<List<SearchResponse>> = MutableLiveData()
    var loadingDialog: MutableLiveData<LoadingDialog> = MutableLiveData()
    var toast: MutableLiveData<ToastDialog> = MutableLiveData()

    private val navigationData: Parameter by lazy { data as Parameter }
    private val searchUseCase: SearchUseCase = SearchUseCase()


    fun searchCall(word: String?) {
        searchUseCase.execute(SearchUseCase.Parameter().apply {
            this.word = word
            this.callback = Callback()
        })
    }

    fun clearCall() {
        searchUseCase.clear()
    }

    fun stopCall() {
        searchUseCase.stop()
    }

    interface ICallback : BaseViewModel.ICallback<List<SearchResponse>, ApiException>

    inner class Callback : ICallback {
        override fun onReady() {
            loadingDialog.value =  LoadingDialog(true)
        }

        override fun onDone() {
            loadingDialog.value = LoadingDialog(false)
        }

        override fun onSuccess(response: List<SearchResponse>) {
            searchResponse.value = response
        }

        override fun onFail(exception: ApiException) {
            when (exception) {
                ApiException.NoInternetConnection -> toast.value = ToastDialog(
                    R.string.network_error,
                    android.widget.Toast.LENGTH_SHORT
                )
                ApiException.NoSuchResult -> toast.value = ToastDialog(
                    R.string.no_results_found,
                    Toast.LENGTH_SHORT
                )
                ApiException.Timeout -> toast.value = ToastDialog(
                    R.string.request_timeout,
                    Toast.LENGTH_SHORT
                )
            }
        }
    }

    class Parameter : BaseViewModel.Parameter()
}

