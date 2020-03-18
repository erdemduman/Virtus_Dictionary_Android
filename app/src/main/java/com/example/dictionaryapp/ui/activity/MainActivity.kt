package com.example.dictionaryapp.ui.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.activity.base.BaseActivity
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.adapter.ViewPagerAdapter
import com.example.dictionaryapp.ui.fragmentview.SearchOuterFragment
import com.example.dictionaryapp.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding, MainActivityViewModel.Parameter>() {

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PagerAdapter

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun initParameter() {
        TODO("Not yet implemented")
    }

    override fun initViewModel() {
        binding.viewModel = this.viewModel
    }

    override fun initView() {
        viewPager = findViewById(R.id.view_pager_main_activity)
        viewModel.retroCall()
    }

    override fun initObserver() {
        viewModel.searchResponse.observe(this, Observer { response -> initViewPager(response)})
    }

    override fun passParameter(): MainActivityViewModel.Parameter {
        var navigationData = MainActivityViewModel.Parameter()
        navigationData.application = application

        return navigationData
    }

    private fun initViewPager(response: List<SearchResponse>) {
        viewPagerAdapter = ViewPagerAdapter(getPages(response), supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
    }

    private fun getPages(response: List<SearchResponse>): List<Fragment>{
        var items: MutableList<Fragment> = arrayListOf()
        items.add(SearchOuterFragment.create(response[0]))
        items.add(SearchOuterFragment.create(response[1]))
        items.add(SearchOuterFragment.create(response[2]))

        return items
    }
}
