package com.travels.android.main.search.core.widget

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.AlertDialogLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Button
import android.widget.LinearLayout
import com.jakewharton.rxrelay2.BehaviorRelay
import com.travels.android.main.R


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
    private val addRouteButton: Button

    private val routeLayoutAdapter = RouteLayoutAdapter(this)

    private val itemTouchHelper: ItemTouchHelper

    init {

        routesRecyclerView = RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutManager = LinearLayoutManager(context)
            adapter = routeLayoutAdapter.apply {
            }
            isNestedScrollingEnabled = false
        }

        addRouteButton = Button(context).apply {
            layoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            text = context.getString(R.string.add_new_route)
            setOnClickListener { openNewRouteDialog() }
        }

//        val container = LinearLayout(context).apply {
//            orientation = LinearLayout.VERTICAL
//            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
//            addView(routesRecyclerView)
//            addView(addRouteButton)
//        }
        addView(routesRecyclerView)

        val callback = SimpleItemTouchHelperCallback(routeLayoutAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(routesRecyclerView)
    }

    override fun onMoveFinished() {
        routeChanges.accept(routeLayoutAdapter.routes)
    }

    fun setRoutes(routes: List<RouteItem>) {
        val newList = listOf(*routes.toTypedArray(), RouteItem(null, null))
        routeLayoutAdapter.setRoutes(newList)
    }

    private fun openNewRouteDialog() {
        CreateOrUpdateRouteDialog(context, { routeItem ->
            routeLayoutAdapter.addRoute(routeItem)
            routeChanges.accept(routeLayoutAdapter.routes)
        }).show()
    }
}
