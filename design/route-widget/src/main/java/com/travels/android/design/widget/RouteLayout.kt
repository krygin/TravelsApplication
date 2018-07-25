package com.travels.android.design.widget

import android.app.DatePickerDialog
import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.travels.android.design.widget.model.Place
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class RouteLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr), OnStartDragListener {

    private val dateFormat: DateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT)

    private val disposables = CompositeDisposable()

    val routeChanges
        get() = routeLayoutAdapter.routeChanges

    val placePickerEditTextChanges = BehaviorRelay.create<CharSequence>()

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    private val routesRecyclerView: RecyclerView
    private val dateRangeSummaryTextView: TextView

    private val routeLayoutAdapter = RouteLayoutAdapter(
            this,
            { openSelectRouteDialog(it) },
            { openSelectArrivalDateDialog(it) },
            { openSelectDepartureDateDialog(it) }
    )

    private val itemTouchHelper: ItemTouchHelper

    private val selectPlaceDialog: SelectPlaceDialog

    init {

        routesRecyclerView = RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutManager = LinearLayoutManager(context)
            adapter = routeLayoutAdapter.apply {
            }
            isNestedScrollingEnabled = false
        }

        dateRangeSummaryTextView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

        }

        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            addView(routesRecyclerView)
            addView(dateRangeSummaryTextView)
        }
        addView(container)

        val callback = SimpleItemTouchHelperCallback(routeLayoutAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(routesRecyclerView)


        disposables += routeLayoutAdapter.routeChanges.subscribe {
            val suggestedEarliestDate = routeLayoutAdapter.getTheEarliestDate() ?: ""
            val suggestedLatestDate = routeLayoutAdapter.getTheLatestDate() ?: ""
            dateRangeSummaryTextView.text = String.format("from ${dateFormat.format(suggestedEarliestDate)} to ${dateFormat.format(suggestedLatestDate)}")
        }

        selectPlaceDialog = SelectPlaceDialog(context).apply {
            searchEditTextChanges().filter { it.length > 2 }.subscribe { placePickerEditTextChanges.accept(it) }
        }
    }

    fun setRoutes(routes: List<RouteItem>) {
        val newList = listOf(*routes.toTypedArray(), RouteItem(null, null, null))
        routeLayoutAdapter.setRoutes(newList)
    }

    fun setPlaces(list: List<Place>) {
        selectPlaceDialog.setPlaces(list)
    }

    private fun openSelectRouteDialog(position: Int) {
        selectPlaceDialog.setOnSelectPlaceListener {
            routeLayoutAdapter.updateRoutePlace(position, it)
        }
        selectPlaceDialog.show()
    }

    private fun openSelectArrivalDateDialog(position: Int) {
        DatePickerDialog(context, { view, year, month, dayOfMonth ->
            routeLayoutAdapter.updateRouteArrivalDate(position, Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time)
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun openSelectDepartureDateDialog(position: Int) {
        DatePickerDialog(context, { view, year, month, dayOfMonth ->
            routeLayoutAdapter.updateRouteDepartureDate(position, Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time)
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
    }
}