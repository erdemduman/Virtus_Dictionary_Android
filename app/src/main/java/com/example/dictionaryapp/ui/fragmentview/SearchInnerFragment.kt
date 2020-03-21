package com.example.dictionaryapp.ui.fragmentview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionaryapp.R
import com.example.dictionaryapp.constant.BundleKey
import com.example.dictionaryapp.databinding.SearchInnerFragmentBinding
import com.example.dictionaryapp.model.KindResponse
import com.example.dictionaryapp.model.MeaningResponse
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.fragmentview.base.BaseFragmentView

class SearchInnerFragment : BaseFragmentView<SearchInnerFragmentBinding>() {

    private lateinit var args: MeaningResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateFragmentView(inflater, container, false)

        return fragmentView
    }

    override fun getLayoutRes(): Int = R.layout.search_inner_fragment

    override fun initParameter() {
        args = arguments?.getSerializable(BundleKey.searchInnerFragment) as MeaningResponse
    }

    override fun initSelf() {
        binding.self = this
    }

    override fun initView() {

    }

    companion object Factory {
        fun create(param: MeaningResponse): SearchInnerFragment {
            var fragment = SearchInnerFragment()
            var bundle = Bundle()
            bundle.putSerializable(BundleKey.searchInnerFragment, param)
            fragment.arguments = bundle

            return fragment
        }
    }
}