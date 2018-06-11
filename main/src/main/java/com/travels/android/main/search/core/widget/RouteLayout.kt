package com.travels.android.main.search.core.widget

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.MotionEvent
import com.jakewharton.rxrelay2.BehaviorRelay
import kotlinx.android.synthetic.main.list_search_fragment.view.*


class RouteLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr), OnStartDragListener {

    val routeChanges = BehaviorRelay.create<List<RouteItem>>()

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    private val routesRecyclerView: RecyclerView
    private val adapter: RouteLayoutAdapter

    private var itemTouchHelper: ItemTouchHelper

    init {
        routesRecyclerView = RecyclerView(context).apply {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        }
        adapter = RouteLayoutAdapter(this)
        routesRecyclerView.layoutManager = LinearLayoutManager(context)
        routesRecyclerView.adapter = adapter
        addView(routesRecyclerView)

        val callback = SimpleItemTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(routesRecyclerView)

    }

    override fun onMoveFinished() {
        routeChanges.accept(adapter.routeList)
    }

    fun setRoutes(routes: List<RouteItem>) {
        adapter.setRoutes(routes)
    }
}
