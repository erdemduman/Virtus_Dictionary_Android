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
import com.example.dictionaryapp.databinding.FragmentSearchBinding
import com.example.dictionaryapp.model.DefinitionResponse
import com.example.dictionaryapp.model.SearchResponse
import com.example.dictionaryapp.ui.adapter.DefinitionRecyclerViewAdapter
import com.example.dictionaryapp.ui.customView.fragmentView.base.BaseFragmentView
import java.io.Serializable

class SearchFragment : BaseFragmentView<FragmentSearchBinding>() {

    private lateinit var args: Parameter
    private var responseSize: Int? = null
    var type: MutableLiveData<String> = MutableLiveData()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: DefinitionRecyclerViewAdapter
    private lateinit var recyclerViewLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateFragmentView(layoutInflater, container, false)
        return fragmentView
    }

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initParameter() {
        args = arguments?.getSerializable(BundleKey.searchFragment) as Parameter
    }

    override fun initSelf() {
        binding.self = this
    }

    override fun initView() {
        type.value = args.type
        var content = args.content

        recyclerViewAdapter = DefinitionRecyclerViewAdapter(content)
        recyclerViewLayoutManager = LinearLayoutManager(context)
        recyclerView = (fragmentView.findViewById(R.id.definition_recycler_view) as RecyclerView).apply {
            adapter = recyclerViewAdapter
            layoutManager = recyclerViewLayoutManager
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    class Parameter : Serializable{
        lateinit var type: String
        lateinit var content: List<DefinitionResponse>
    }

    companion object Factory {
        fun create(param: Parameter): SearchFragment {
            var fragment = SearchFragment()
            var bundle = Bundle()
            bundle.putSerializable(BundleKey.searchFragment, param)
            fragment.arguments = bundle

            return fragment
        }
    }
}
