package com.virtusdictionary.app.ui.viewmodel.base

import android.app.Application
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel(), IBaseViewModel {

    open class Parameter {
        lateinit var application: Application
    }

    interface ICallback<ReturnType, ExceptionType> {
        fun onReady()
        fun onDone()
        fun onSuccess(response: ReturnType)
        fun onFail(exception: ExceptionType)
    }
}