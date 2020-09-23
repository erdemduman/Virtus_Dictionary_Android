package com.virtusdictionary.app.ui.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.virtusdictionary.app.util.ViewModelFactory
import com.virtusdictionary.app.ui.viewmodel.base.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding, ND : Any> : Fragment(),
    IBaseUI<VM, DB, ND> {

    override val binding by lazy {
        DataBindingUtil.inflate(inflater, getLayoutRes(), container, false) as DB
    }

    override val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(passParameter())).get(getViewModel())
    }

    private lateinit var inflater: LayoutInflater
    private lateinit var container: ViewGroup
    protected lateinit var fragmentView: View

    protected fun onCreateFragment(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean = false) {
        this.inflater = layoutInflater
        this.container = container!!

        initParameter()
        initViewModel()
        initObserver()
        initView()
        this.fragmentView = binding.root
    }

    abstract override fun getLayoutRes(): Int

    abstract override fun getViewModel(): Class<out VM>

    abstract override fun initParameter()

    abstract override fun initViewModel()

    abstract override fun initView()

    abstract override fun initObserver()

    abstract override fun passParameter(): ND
}