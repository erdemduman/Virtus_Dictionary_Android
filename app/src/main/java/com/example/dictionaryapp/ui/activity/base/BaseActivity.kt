package com.example.dictionaryapp.ui.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.viewmodel.base.BaseViewModel
import com.example.dictionaryapp.util.ViewModelFactory


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding, ND : Any> : AppCompatActivity(), BaseUI{

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(getParameter())).get(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        initObserver()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getViewModel(): Class<out VM>

    protected abstract fun initView()

    protected abstract fun initViewModel()

    protected abstract fun initObserver()

    protected abstract fun getParameter(): ND
}