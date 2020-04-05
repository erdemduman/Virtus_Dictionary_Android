package com.example.dictionaryapp.ui.customView.fragmentView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.constant.BundleKey
import com.example.dictionaryapp.databinding.FragmentSearchInnerBinding
import com.example.dictionaryapp.model.KindResponse
import com.example.dictionaryapp.ui.adapter.DefinitionRecyclerViewAdapter
import com.example.dictionaryapp.ui.customView.fragmentView.base.BaseFragmentView

class SearchInnerFragment : BaseFragmentView<FragmentSearchInnerBinding>() {

    private lateinit var keyArg: String
    private lateinit var valueArg: List<KindResponse>

    lateinit var title: MutableLiveData<String>

    private lateinit var definitionRecyclerView: RecyclerView
    private lateinit var definitionAdapter: DefinitionRecyclerViewAdapter
    private lateinit var definitionLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateFragmentView(inflater, container, false)

        return fragmentView
    }

    override fun getLayoutRes(): Int = R.layout.fragment_search_inner

    override fun initParameter() {
        keyArg = arguments?.getSerializable(BundleKey.searchInnerFragmentKey) as String
        valueArg = arguments?.getSerializable(BundleKey.searchInnerFragmentValue) as ArrayList<KindResponse>
    }

    override fun initSelf() {
        binding.self = this
    }

    override fun initView() {
        title = MutableLiveData()
        title.value = keyArg

        definitionAdapter = DefinitionRecyclerViewAdapter(valueArg)
        definitionLayoutManager = LinearLayoutManager(context)
        definitionRecyclerView = fragmentView.findViewById<RecyclerView>(R.id.definition_recycler_view).apply{
            adapter = definitionAdapter
            layoutManager = definitionLayoutManager
        }
    }

    companion object Factory {
        fun create(key: String, value: ArrayList<KindResponse>): SearchInnerFragment {
            var fragment = SearchInnerFragment()
            var bundle = Bundle()
            bundle.putSerializable(BundleKey.searchInnerFragmentKey, key)
            bundle.putSerializable(BundleKey.searchInnerFragmentValue, value)
            fragment.arguments = bundle

            return fragment
        }
    }
}