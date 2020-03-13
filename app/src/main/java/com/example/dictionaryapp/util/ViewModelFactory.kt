package com.example.dictionaryapp.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.viewmodel.MainActivityViewModel

class ViewModelFactory(private val navigationData: Any) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass){
            MainActivityViewModel::class.java -> return MainActivityViewModel(navigationData) as T

            else -> throw NoSuchElementException()
        }
    }
}