package com.example.dictionaryapp.ui.fragmentview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.R
import com.example.dictionaryapp.constant.BundleKey
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.fragmentview.base.BaseFragmentView
import java.io.Serializable

class SearchOuterFragment : BaseFragmentView() {

    private lateinit var args: SearchResponse

    lateinit var word: MutableLiveData<String>
    lateinit var phonetic: MutableLiveData<String>
    lateinit var origin: MutableLiveData<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateFragmentView(layoutInflater, container, false)
        return fragmentView
    }

    override fun getLayoutRes(): Int = R.layout.search_outer_fragment

    override fun initParameter() {
        args = arguments?.getSerializable(BundleKey.searchOuterFragment) as SearchResponse
    }

    override fun initSelf() {
        binding.self = this
    }

    override fun initView() {
        word = MutableLiveData()
        phonetic = MutableLiveData()
        origin = MutableLiveData()
        
        word.value = args.word
        phonetic.value = args.phonetic
        origin.value = args.origin
    }

    companion object Factory {
        fun create(param: SearchResponse): SearchOuterFragment {
            var fragment = SearchOuterFragment()
            var bundle = Bundle()
            bundle.putSerializable(BundleKey.searchOuterFragment, param)
            fragment.arguments = bundle

            return fragment
        }
    }
}
