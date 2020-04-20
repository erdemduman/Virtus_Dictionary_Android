package com.virtusdictionary.app.ui.customView.fragmentView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.virtusdictionary.app.R
import com.virtusdictionary.app.constant.BundleKey
import com.virtusdictionary.app.databinding.FragmentSearchOuterBinding
import com.virtusdictionary.app.model.SearchResponse
import com.virtusdictionary.app.ui.adapter.ViewPagerAdapter
import com.virtusdictionary.app.ui.customView.fragmentView.base.BaseFragmentView
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class SearchOuterFragment : BaseFragmentView<FragmentSearchOuterBinding>() {

    private lateinit var args: SearchResponse

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var viewPagerIndicator: WormDotsIndicator

    private var responseSize: Int? = null

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

    override fun getLayoutRes(): Int = R.layout.fragment_search_outer

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
        viewPagerIndicator = fragmentView.findViewById(R.id.worm_dots_indicator_outer)
        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(getPages(), childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        initViewPagerIndicator()
    }

    private fun initViewPagerIndicator(){
        viewPagerIndicator.setViewPager(viewPager)
        viewPagerIndicator.visibility = if(responseSize!! > 1) View.VISIBLE else View.GONE
    }

    private fun getPages(): MutableList<Fragment> {
        var items: MutableList<Fragment> = arrayListOf()
        for ((k, v) in args.meaning) {
            items.add(SearchInnerFragment.create(k, v))
        }
        responseSize = args.meaning.size
        return items
    }

    override fun onDestroy() {
        super.onDestroy()
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
