package com.travels.android.design.widget

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_RANGE
import java.util.*

class DateRangePicker : DialogFragment() {

    private lateinit var dateRangeSelectedListener: (from: Date, to: Date) -> Unit


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val materialCalendarView = MaterialCalendarView(context).apply {
            selectionMode = SELECTION_MODE_RANGE
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return AlertDialog.Builder(context, theme)
                .setView(materialCalendarView)
                .setTitle("Select date range")
                .setPositiveButton("Confirm") { dialog, which ->
                    val selectedDates = materialCalendarView.selectedDates
                    if (selectedDates.isNotEmpty()) {
                        dateRangeSelectedListener(selectedDates.first().date, selectedDates.last().date)
                    }
                }
                .setNeutralButton("Cancel", { dialog, which -> })
                .create()
    }

    fun setOnDateRangeSelectedListener(dateRangeSelectedListener: (from: Date, to: Date) -> Unit): DateRangePicker {
        this.dateRangeSelectedListener = dateRangeSelectedListener
        return this
    }

}