package com.example.dictionaryapp.ui.fragmentview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.constant.BundleKey
import com.example.dictionaryapp.databinding.SearchOuterFragmentBinding
import com.example.dictionaryapp.model.KindResponse
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.adapter.ViewPagerAdapter
import com.example.dictionaryapp.ui.fragmentview.base.BaseFragmentView
import java.io.Serializable

class SearchOuterFragment : BaseFragmentView<SearchOuterFragmentBinding>() {

    private lateinit var args: SearchResponse

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PagerAdapter

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

        viewPager = fragmentView.findViewById(R.id.inner_view_pager)
        viewPagerAdapter = ViewPagerAdapter(getPages(), childFragmentManager!!)
        viewPager.adapter = viewPagerAdapter
    }

    private fun getPages(): MutableList<Fragment>{
        var items: MutableList<Fragment> = arrayListOf()
        items.add(SearchInnerFragment.create(args.meaning))
        items.add(SearchInnerFragment.create(args.meaning))
        items.add(SearchInnerFragment.create(args.meaning))
        items.add(SearchInnerFragment.create(args.meaning))

        return items
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
