package com.example.dictionaryapp.viewmodel.base

import android.app.Application
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {

    open class Parameter {
        lateinit var application: Application
    }
}