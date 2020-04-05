package com.example.dictionaryapp.ui.activity

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.activity.base.BaseActivity
import com.example.dictionaryapp.ui.adapter.ViewPagerAdapter
import com.example.dictionaryapp.ui.customView.fragmentView.SearchOuterFragment
import com.example.dictionaryapp.viewmodel.MainActivityViewModel
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

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
        viewModel.showNoConnection.observe(this, Observer { _ -> showNoConnectionToast()})
        viewModel.showNoSuchWord.observe(this, Observer { _ -> showNoSuchWordToast() })
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

    private fun showNoConnectionToast() {
        Toast.makeText(applicationContext, R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    private fun showNoSuchWordToast(){
        Toast.makeText(applicationContext, R.string.no_results_found, Toast.LENGTH_LONG).show();
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopCall()
    }
}
