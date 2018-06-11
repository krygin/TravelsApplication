package com.travels.android.main.search.core.widget

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.travels.android.main.R
import android.graphics.Color.LTGRAY



class RouteLayoutAdapter(private val startDragListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {


    private val routeList = mutableListOf<RouteItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = RouteViewHolder.getItemView(parent.context, parent)
        return RouteViewHolder(itemView)
    }

    override fun getItemCount(): Int = routeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = routeList[position]

        (holder as RouteViewHolder).apply {
            holder.placeTextView.text = item.place.name
            holder.itemView.setOnTouchListener { v, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(holder)
                }
                false
            }

        }
    }

    fun addRouteItem(routeItem: RouteItem) {
        routeList.add(routeItem)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val prev = routeList.removeAt(fromPosition)
        routeList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        routeList.removeAt(position);
        notifyItemRemoved(position);
    }
}

private class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

    val placeTextView = itemView.findViewById<TextView>(R.id.place_text_view)

    companion object {
        fun getItemView(context: Context, parent: ViewGroup): View = LayoutInflater.from(context).inflate(R.layout.readonly_route_item, parent, false)
    }


    override fun onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun onItemClear() {
        itemView.setBackgroundColor(0)
    }

}

private const val ROUTE_VIEW_TYPE = 0
private const val NEW_ROUTE_VIEW_TYPE = 1

private const val EDITABLE_MODE = 0
private const val READ_ONLY_MODE = 1