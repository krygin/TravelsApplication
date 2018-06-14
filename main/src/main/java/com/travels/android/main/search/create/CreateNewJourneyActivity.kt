package com.travels.android.main.search.create

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.travels.android.main.R
import com.travels.android.main.search.core.Itinerary
import com.travels.android.main.search.core.PointInfo
import com.travels.android.main.search.core.widget.RouteItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_create_journey.*

class CreateNewJourneyActivity : AppCompatActivity() {

    val disposables = CompositeDisposable()

    private lateinit var viewModel: CreateNewJourneyViewModel

    private lateinit var itinerary: Itinerary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journey)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this)
                .get(CreateNewJourneyViewModel::class.java)
        viewModel.itiniary.observe(this, Observer {
            it?.let {
                route_layout.setRoutes(it.places.map { RouteItem(it.place, it.arrival) })
            }
        })

        val subscription = route_layout.routeChanges.subscribe {
            it?.let {
                viewModel.updateJourneyRoutes(it.map { PointInfo(it.place!!, it.date, it.date) })
            }

        }
        disposables.add(subscription)
    }
}