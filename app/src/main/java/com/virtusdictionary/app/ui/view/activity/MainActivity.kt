package com.virtusdictionary.app.ui.view.activity

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.virtusdictionary.app.R
import com.virtusdictionary.app.databinding.ActivityMainBinding
import com.virtusdictionary.app.data.SearchResponse
import com.virtusdictionary.app.ui.view.activity.base.BaseActivity
import com.virtusdictionary.app.ui.view.adapter.ViewPagerAdapter
import com.virtusdictionary.app.ui.view.customView.fragmentView.SearchOuterFragment
import com.virtusdictionary.app.ui.viewmodel.MainActivityViewModel
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.virtusdictionary.app.ui.view.popup.ToastDialog

class MainActivity :
    BaseActivity<MainActivityViewModel, ActivityMainBinding, MainActivityViewModel.Parameter>() {

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var searchView: SearchView
    private lateinit var viewpagerIndicator: WormDotsIndicator
    private var responseSize: Int? = null

    private val inputManager by lazy {
        applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun initParameter() {
        TODO("Not yet implemented")
    }

    override fun initViewModel() {
        binding.viewModel = this.viewModel
    }

    override fun initObserver() {
        viewModel.searchResponse.observe(this, Observer { response -> initViewPager(response) })
        viewModel.toast.observe(this, Observer { observable -> showToast(observable) })
    }

    override fun initView() {
        viewPager = findViewById(R.id.view_pager_main_activity)
        searchView = findViewById(R.id.word_search_view)
        viewpagerIndicator = findViewById(R.id.worm_dots_indicator_activity)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                inputManager.toggleSoftInput(0, 0)
                viewModel.searchCall(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun passParameter(): MainActivityViewModel.Parameter {
        var navigationData = MainActivityViewModel.Parameter()
        navigationData.application = application

        return navigationData
    }

    private fun initViewPager(response: List<SearchResponse>) {
        viewPagerAdapter = ViewPagerAdapter(getPages(response), supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        initViewPagerIndicator()
    }

    private fun initViewPagerIndicator() {
        viewpagerIndicator.setViewPager(viewPager)
        viewpagerIndicator.visibility = if (responseSize!! > 1) View.VISIBLE else View.GONE
    }

    private fun getPages(response: List<SearchResponse>): List<Fragment> {
        var items: MutableList<Fragment> = arrayListOf()
        for (i in response) {
            if (i.meaning.isNotEmpty())
                items.add(SearchOuterFragment.create(i))
        }
        responseSize = items.size
        return items
    }

    private fun showToast(toast: ToastDialog) {
        Toast.makeText(applicationContext, getString(toast.text.value!!), toast.duration.value!!).show()
    }

    override fun onPause() {
        super.onPause()
        viewModel.clearCall()
    }

    override fun onStop() {
        super.onStop()
        viewModel.clearCall()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopCall()
    }
}
