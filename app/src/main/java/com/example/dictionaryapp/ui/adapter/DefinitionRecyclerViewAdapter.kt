package com.example.dictionaryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.DefinitionResponse
import com.example.dictionaryapp.ui.customView.fragmentView.SearchFragment
import java.util.*

class DefinitionRecyclerViewAdapter(private val items: List<DefinitionResponse>) :
    RecyclerView.Adapter<DefinitionRecyclerViewAdapter.DefinitionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_definition, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        var item = items[position]
        setViewHolderVisibility(holder, item)
        setSeparatorVisibility(holder, position)
    }

    @ExperimentalStdlibApi
    private fun setViewHolderVisibility(holder: DefinitionViewHolder, item: DefinitionResponse) {
        var nullCounter = 0

        if (item.definition == null)
            nullCounter++

        if (item.example == null)
            nullCounter++

        when (nullCounter) {
            0 -> {
                holder.definition.text = item.definition.capitalize(Locale.ENGLISH)
                holder.example.text = changeExampleCharacteristic(item.example)
                holder.falseMargin.visibility = View.GONE
            }
            1 -> {
                holder.definition.text = item.definition.capitalize(Locale.ENGLISH)
                holder.example.visibility = View.GONE
                holder.falseMargin.visibility = View.VISIBLE
            }
            2 -> {
                holder.definition.visibility = View.GONE
                holder.example.visibility = View.GONE
                holder.falseMargin.visibility = View.GONE
            }
        }
    }

    @ExperimentalStdlibApi
    private fun changeExampleCharacteristic(example: String): String {
        var cap = example.trimStart('\n')
            .trimStart(' ')
            .capitalize(Locale.ENGLISH)

        return if (cap[cap.length - 1] == '?' ||
            cap[cap.length - 1] == '!' ||
            cap[cap.length - 1] == '.'
        ) cap else "$cap."
    }

    private fun setSeparatorVisibility(holder: DefinitionViewHolder, position: Int) {
        if (position == items.size - 1)
            holder.separator.visibility = View.GONE
        else if (items[position + 1].definition == null) {
            holder.separator.visibility = View.GONE
        }
    }

    class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var definition: TextView = itemView.findViewById(R.id.definition_card_definition)
        var example: TextView = itemView.findViewById(R.id.definition_card_example)
        var separator: View = itemView.findViewById(R.id.definition_separator)
        var falseMargin: View = itemView.findViewById(R.id.falseMargin)
    }
}