package com.virtusdictionary.app.viewmodel.base

import android.app.Application
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {

    open class Parameter {
        lateinit var application: Application
    }
}