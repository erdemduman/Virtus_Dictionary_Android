package com.virtusdictionary.app.api.useCase.base

import com.virtusdictionary.app.viewmodel.base.BaseViewModel
import java.lang.Exception

abstract class BaseUseCase {
    open class Parameter<ReturnType, ExceptionType> {
        var callback: BaseViewModel.ICallback<ReturnType, ExceptionType>? = null
    }
}