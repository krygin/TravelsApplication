package com.travels.android.main.search.filter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.travels.android.design.widget.DateRangePicker
import com.travels.android.main.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.DateFormat

class JourneysFilterActivity : AppCompatActivity() {

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journeys_filter)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dateRangeTextView = findViewById<TextView>(R.id.date_range_text_view)
        disposables += dateRangeTextView.clicks().subscribe {
            DateRangePicker()
                    .setOnDateRangeSelectedListener { from, to -> dateRangeTextView.text =
                            "From ${DateFormat.getDateInstance(DateFormat.SHORT).format(from)} to ${DateFormat.getDateInstance(DateFormat.SHORT).format(to)}" }
                    .show(supportFragmentManager, null) }
    }
}

private operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    add(subscribe)
}
