package com.travels.android.main.search.filter

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.clicks
import com.travels.android.design.widget.DateRangePicker
import com.travels.android.main.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_journeys_filter.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class JourneysFilterActivity : AppCompatActivity() {

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journeys_filter)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        disposables += date_range_text_view.clicks().subscribe {
            DateRangePicker()
                    .setOnDateRangeSelectedListener { from, to -> date_range_text_view.text =
                            "From ${DateFormat.getDateInstance(DateFormat.SHORT).format(from)} to ${DateFormat.getDateInstance(DateFormat.SHORT).format(to)}" }
                    .show(supportFragmentManager, null) }
    }
}

private operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    add(subscribe)
}
