package com.virtusdictionary.app.ui.customView.fragmentView.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragmentView<DB : ViewDataBinding> : Fragment() {

    protected lateinit var binding: DB
    protected lateinit var fragmentView: View

    fun onCreateFragmentView(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, attachToRoot)
        fragmentView = binding.root
        binding.lifecycleOwner = this
        initParameter()
        initSelf()
        initView()
    }

    abstract fun getLayoutRes(): Int

    abstract fun initParameter()

    abstract fun initSelf()

    abstract fun initView()

}