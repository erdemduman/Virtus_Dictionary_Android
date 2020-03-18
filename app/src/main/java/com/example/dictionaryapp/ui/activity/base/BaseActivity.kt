package com.example.dictionaryapp.ui.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.ui.base.IBaseUI
import com.example.dictionaryapp.viewmodel.base.BaseViewModel
import com.example.dictionaryapp.util.ViewModelFactory


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding, ND : Any> :
    AppCompatActivity(), IBaseUI<VM, DB, ND> {

    override val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    override val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(passParameter())).get(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObserver()
        initView()
    }

    @LayoutRes
    abstract override fun getLayoutRes(): Int

    abstract override fun getViewModel(): Class<out VM>

    abstract override fun initParameter()

    abstract override fun initView()

    abstract override fun initViewModel()

    abstract override fun initObserver()

    abstract override fun passParameter(): ND
}