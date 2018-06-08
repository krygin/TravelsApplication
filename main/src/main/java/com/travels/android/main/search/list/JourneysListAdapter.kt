package com.travels.android.main.search.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travels.android.main.R
import kotlinx.android.synthetic.main.journey_list_item.view.*

class JourneysListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<JourneyItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.journey_list_item, parent, false)
        return JourneyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val journey = items[position]
        when (holder) {
            is JourneyViewHolder -> {
                holder.nameTextView.text = journey.name
            }
        }
    }

    fun setItems(items: List<JourneyItem>) {
        this.items.clear()
        this.items.addAll(items)
    }
}


class JourneyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView = itemView.name
}

class JourneyItem(val name: String)