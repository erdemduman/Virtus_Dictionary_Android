package com.virtusdictionary.app.ui.view.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.virtusdictionary.app.ui.view.base.IBaseUI
import com.virtusdictionary.app.util.ViewModelFactory
import com.virtusdictionary.app.ui.viewmodel.base.BaseViewModel


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
        initLifecycleOwner()
        initViewModel()
        initObserver()
        initView()
    }

    private fun initLifecycleOwner() {
        binding.lifecycleOwner = this
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