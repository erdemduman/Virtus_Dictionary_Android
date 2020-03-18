package com.example.dictionaryapp.ui.fragmentview.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.databinding.SearchOuterFragmentBinding

abstract class BaseFragmentView : Fragment() {

    protected lateinit var binding: SearchOuterFragmentBinding
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