package com.example.dictionaryapp.ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.example.dictionaryapp.viewmodel.base.BaseViewModel

interface IBaseUI <VM : BaseViewModel, DB : ViewDataBinding, ND : Any> {

    val binding: DB
    val viewModel: VM

    @LayoutRes
    fun getLayoutRes(): Int

    fun getViewModel(): Class<out VM>

    fun initParameter()

    fun initViewModel()

    fun initObserver()

    fun initView()

    fun passParameter(): ND
}