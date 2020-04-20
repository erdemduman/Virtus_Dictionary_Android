package com.virtusdictionary.app.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virtusdictionary.app.viewmodel.MainActivityViewModel

class ViewModelFactory(private val navigationData: Any) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            MainActivityViewModel::class.java -> return MainActivityViewModel(navigationData) as T

            else -> throw NoSuchElementException()
        }
    }
}