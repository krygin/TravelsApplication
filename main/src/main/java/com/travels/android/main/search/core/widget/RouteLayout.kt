package com.travels.android.main.search.core.widget

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import android.support.v7.widget.helper.ItemTouchHelper



class RouteLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr), OnStartDragListener {
    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    private val routesRecyclerView: RecyclerView

    private var itemTouchHelper: ItemTouchHelper

    init {
        routesRecyclerView = RecyclerView(context).apply {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        }
        routesRecyclerView.layoutManager = LinearLayoutManager(context)
        routesRecyclerView.adapter = RouteLayoutAdapter(this)
        addView(routesRecyclerView)

        val callback = SimpleItemTouchHelperCallback(routesRecyclerView.adapter as RouteLayoutAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(routesRecyclerView)

    }

    fun addRoute(routeItem: RouteItem) {
        (routesRecyclerView.adapter as RouteLayoutAdapter).addRouteItem(routeItem)
    }
}
