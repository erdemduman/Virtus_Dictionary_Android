package com.example.dictionaryapp.ui.activity

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.activity.base.BaseActivity
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.ui.adapter.ViewPagerAdapter
import com.example.dictionaryapp.ui.fragment.SearchResultFragment
import com.example.dictionaryapp.ui.activity.contract.IMainActivity
import com.example.dictionaryapp.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding, MainActivityViewModel.Parameter>(), IMainActivity {

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PagerAdapter

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun initView() {
        viewPager = findViewById(R.id.view_pager_main_activity)
        viewPagerAdapter = ViewPagerAdapter(getPages(), supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
    }

    override fun initViewModel() {
        binding.viewModel = this.viewModel
    }

    override fun initObserver() {

    }

    override fun getParameter(): MainActivityViewModel.Parameter {
        var navigationData = MainActivityViewModel.Parameter()
        navigationData.application = application

        return navigationData
    }

    private fun getPages(): List<Fragment>{
        var items: MutableList<Fragment> = arrayListOf()
        items.add(SearchResultFragment.create())
        items.add(SearchResultFragment.create())
        items.add(SearchResultFragment.create())
        items.add(SearchResultFragment.create())

        return items
    }
}