package com.example.dictionaryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.KindResponse

class DefinitionRecyclerViewAdapter(private val items: List<KindResponse>) : RecyclerView.Adapter<DefinitionRecyclerViewAdapter.DefinitionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_definition, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        var item = items[position]
        setViewHolderVisibility(holder, item)
        setSeparatorVisibility(holder, position)
    }

    private fun setViewHolderVisibility(holder: DefinitionViewHolder, item: KindResponse) {
        var nullCounter = 0

        if (item.definition == null)
            nullCounter++

        if (item.example == null)
            nullCounter++

        when (nullCounter) {
            0 -> {
                holder.definition.text = item.definition
                holder.example.text = item.example.capitalize() + "."
                holder.falseMargin.visibility = View.GONE
            }
            1 -> {
                holder.definition.text = item.definition
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