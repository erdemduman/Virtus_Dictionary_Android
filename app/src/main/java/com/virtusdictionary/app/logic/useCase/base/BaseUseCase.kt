package com.virtusdictionary.app.logic.useCase.base

import com.virtusdictionary.app.ui.viewmodel.base.BaseViewModel

abstract class BaseUseCase {
    open class Parameter<ReturnType, ExceptionType> {
        var callback: BaseViewModel.ICallback<ReturnType, ExceptionType>? = null
    }
}