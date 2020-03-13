package com.example.dictionaryapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.fragment.base.BaseFragment
import com.example.dictionaryapp.ui.fragment.contract.ISearchResultFragment

class SearchResultFragment : BaseFragment(), ISearchResultFragment {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.search_result_fragment, container, false)
        return v
    }

    companion object Factory{
        fun create() : SearchResultFragment {
            return SearchResultFragment()
        }
    }
}