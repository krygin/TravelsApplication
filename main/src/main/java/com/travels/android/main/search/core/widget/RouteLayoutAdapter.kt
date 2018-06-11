package com.travels.android.main.search.core.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.travels.android.main.R


class RouteLayoutAdapter(private val startDragListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {


    val routeList = mutableListOf<RouteItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = RouteViewHolder.getItemView(parent.context, parent)
        return RouteViewHolder(itemView)
    }

    override fun getItemCount(): Int = routeList.size

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = routeList[position]

        (holder as RouteViewHolder).apply {
            holder.placeTextView.text = item.place.name

            holder.reorderImageButton.setOnTouchListener { v, event ->

                Log.e("TOUCH EVENT", event.toString())

                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(holder)
                }
                false
            }

        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val prev = routeList.removeAt(fromPosition)
        routeList.add(if (toPosition > fromPosition) toPosition else toPosition - 1, prev)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        routeList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onMoveFinished() {
        startDragListener.onMoveFinished()
    }

    fun setRoutes(routes: List<RouteItem>) {
        val diff = DiffUtil.calculateDiff(RouteDiffUtil(this.routeList, routes))
        this.routeList.apply {
            clear()
            addAll(routes)
        }
        diff.dispatchUpdatesTo(this)
    }
}

private class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

    val placeTextView = itemView.findViewById<TextView>(R.id.place_text_view)
    val reorderImageButton = itemView.findViewById<ImageView>(R.id.reorder_image_button)

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


private class RouteDiffUtil(val oldRoutes: List<RouteItem>, val newRoutes: List<RouteItem>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRoutes[oldItemPosition] == newRoutes[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldRoutes.size
    }

    override fun getNewListSize(): Int {
        return newRoutes.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRoutes[oldItemPosition] == newRoutes[newItemPosition]
    }

}

private const val ROUTE_VIEW_TYPE = 0
private const val NEW_ROUTE_VIEW_TYPE = 1

private const val EDITABLE_MODE = 0
private const val READ_ONLY_MODE = 1