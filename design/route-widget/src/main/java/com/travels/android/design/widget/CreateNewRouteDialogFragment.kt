package com.travels.android.design.widget

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.*
import com.jakewharton.rxbinding2.widget.textChanges
import com.travels.android.design.widget.model.Location
import com.travels.android.design.widget.model.Place
import io.reactivex.Observable
import java.util.*

class SelectPlaceDialog(context: Context) : AlertDialog(context) {

    private val editText: EditText

    private val placesListView: ListView

    private var onSelectPlace: ((Place) -> Unit)? = null

    init {

        editText = AppCompatEditText(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
        }

        placesListView = ListView(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            addView(editText)
            addView(placesListView)
        }

        setView(container)
        setTitle("Select route")
        setOnDismissListener { onSelectPlace = null }
    }

    fun searchEditTextChanges() = editText.textChanges()

    fun setOnSelectPlaceListener(listener: (Place) -> Unit) {
        onSelectPlace = listener
    }

    fun setPlaces(places: List<Place>) {
        placesListView.adapter = SimpleAdapter(context, places.map { mapOf(Pair("KEY_NAME", it.title)) }, android.R.layout.simple_list_item_1, arrayOf("KEY_NAME"), arrayListOf(android.R.id.text1).toIntArray())
        placesListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            onSelectPlace?.invoke(Place((placesListView.adapter.getItem(position) as Map<String, String>).getValue("KEY_NAME"), Location(0.0, 0.0)))
            dismiss()
        }
    }
}
