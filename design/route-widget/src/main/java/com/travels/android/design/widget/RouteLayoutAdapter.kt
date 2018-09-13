package com.travels.android.design.widget

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.travels.android.design.widget.model.Place
import com.travels.android.design.widget.route_widget.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class RouteLayoutAdapter(
        private val startDragListener: OnStartDragListener,
        private val onChangePlaceListener: (position: Int) -> Unit,
        private val onChangeArrivalDateListener: (position: Int) -> Unit,
        private val onChangeDepartureDateListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    val routeChanges = BehaviorRelay.create<List<RouteItem>>()

    private val dateFormat: DateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT)

    private val data = mutableListOf<RouteItem>()

    val routes get() = data.filter { it.place != null || it.arrivalDate != null || it.departureDate != null }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ROUTE_ITEM -> {
                val itemView = RouteViewHolder.getItemView(parent.context, parent)
                RouteViewHolder(itemView)
            }
            else -> throw Exception()
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        when (holder) {
            is RouteViewHolder -> {
                holder.placeContainer.setOnClickListener { onChangePlaceListener(holder.adapterPosition) }
                item.place?.let {
                    holder.placeTextView.visibility = View.VISIBLE
                    holder.placeImageView.visibility = View.GONE
                    holder.placeTextView.setText(it.title)
                } ?: run {
                    holder.placeImageView.visibility = View.VISIBLE
                    holder.placeTextView.visibility = View.GONE
                }

                holder.arrivalDateContainer.setOnClickListener { onChangeArrivalDateListener(holder.adapterPosition) }
                item.arrivalDate?.let {
                    holder.arrivalDateTextView.visibility = View.VISIBLE
                    holder.arrivalDateImageView.visibility = View.GONE
                    holder.arrivalDateTextView.setText(dateFormat.format(it))
                } ?: run {
                    holder.arrivalDateImageView.visibility = View.VISIBLE
                    holder.arrivalDateTextView.visibility = View.GONE
                }

                holder.departureDateContainer.setOnClickListener { onChangeDepartureDateListener(holder.adapterPosition) }
                item.departureDate?.let {
                    holder.departureDateTextView.visibility = View.VISIBLE
                    holder.departureDateImageView.visibility = View.GONE
                    holder.departureDateTextView.setText(dateFormat.format(it))
                } ?: run {
                    holder.departureDateImageView.visibility = View.VISIBLE
                    holder.departureDateTextView.visibility = View.GONE
                }

                holder.reorderImageButton.setOnTouchListener { v, event ->

                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        startDragListener.onStartDrag(holder)
                    }
                    false
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return ROUTE_ITEM
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val prev = data.removeAt(fromPosition)
        data.add(if (toPosition > fromPosition) toPosition else toPosition, prev)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onMoveFinished() {
        acceptRouteListChanges()
    }

    fun setRoutes(routes: List<RouteItem>) {
        val diff = DiffUtil.calculateDiff(RouteDiffUtil(this.data.map { it }, routes))
        this.data.apply {
            clear()
            addAll(routes)
        }
        diff.dispatchUpdatesTo(this)
    }

    fun updateRoutePlace(position: Int, place: Place) {
        data[position] = data[position].copy(place = place)
        notifyItemChanged(position)
        acceptRouteListChanges()
    }

    fun updateRouteArrivalDate(position: Int, date: Date) {
        data[position] = data[position].copy(arrivalDate = date)
        notifyItemChanged(position)
        acceptRouteListChanges()
    }

    fun updateRouteDepartureDate(position: Int, date: Date) {
        data[position] = data[position].copy(departureDate = date)
        notifyItemChanged(position)
        acceptRouteListChanges()
    }

    fun getTheEarliestDate(): Date? {
        return routes.filter { it.arrivalDate != null }.minBy { it.arrivalDate!! }?.arrivalDate
    }

    fun getTheLatestDate(): Date? {
        return routes.filter { it.departureDate != null }.maxBy { it.departureDate!! }?.departureDate
    }

    private fun acceptRouteListChanges() {
        routeChanges.accept(routes)
    }
}

private class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val placeContainer = itemView.findViewById<FrameLayout>(R.id.place_container)
    val placeTextView = itemView.findViewById<TextView>(R.id.place_text_view)
    val placeImageView = itemView.findViewById<ImageView>(R.id.place_image_view)

    val arrivalDateContainer = itemView.findViewById<FrameLayout>(R.id.arrival_date_container)
    val arrivalDateTextView = itemView.findViewById<TextView>(R.id.arrival_date_text_view)
    val arrivalDateImageView = itemView.findViewById<ImageView>(R.id.arrival_date_image_view)

    val departureDateContainer = itemView.findViewById<FrameLayout>(R.id.departure_date_container)
    val departureDateTextView = itemView.findViewById<TextView>(R.id.departure_date_text_view)
    val departureDateImageView = itemView.findViewById<ImageView>(R.id.departure_date_image_view)

    val reorderImageButton = itemView.findViewById<ImageView>(R.id.reorder_image_button)

    companion object {
        fun getItemView(context: Context, parent: ViewGroup): View = LayoutInflater.from(context).inflate(R.layout.route_item, parent, false)
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

private const val ROUTE_ITEM = 0