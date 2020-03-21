package com.example.dictionaryapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dictionaryapp.model.SearchResponse

class ViewPagerAdapter(private val items: List<Fragment>, fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        return this.items[position]
    }

    override fun getCount(): Int = this.items.size
}