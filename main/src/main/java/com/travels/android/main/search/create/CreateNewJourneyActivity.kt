package com.travels.android.main.search.create

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.travels.android.base.TravelsApplication
import com.travels.android.base.di.ApplicationProvider
import com.travels.android.base.domain.Response
import com.travels.android.main.R
import com.travels.android.main.search.core.Itinerary
import com.travels.android.main.search.core.PointInfo
import com.travels.android.main.search.core.widget.RouteItem
import com.travels.android.main.search.core.widget.RouteLayout
import com.travels.android.main.search.create.di.DaggerCreateNewJourneyComponent
import com.travels.android.main.search.create.util.CreateNewJourneyViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_create_journey.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CreateNewJourneyActivity : AppCompatActivity(), ApplicationProvider {

    @Inject
    lateinit var createNewJourneyViewModelFactory: CreateNewJourneyViewModelFactory

    val disposables = CompositeDisposable()

    private lateinit var viewModel: CreateNewJourneyViewModel

    private lateinit var itinerary: Itinerary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCreateNewJourneyComponent.builder().inject(this)
        setContentView(R.layout.activity_create_journey)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val routeLayout = findViewById<RouteLayout>(R.id.route_layout)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, createNewJourneyViewModelFactory)
                .get(CreateNewJourneyViewModel::class.java)
        viewModel.itiniary.observe(this, Observer {
            it?.let {
                routeLayout.setRoutes(it.places.map { RouteItem(it.place, it.arrival, it.departure) })
            }
        })
        viewModel.places.observe(this, Observer {
            it?.let {
                when (it) {
                    is Response.Loading -> {}
                    is Response.Success -> routeLayout.setPlaces(it.data)
                    is Response.Failure -> {}
                }
            }
        })
        disposables += routeLayout.placePickerEditTextChanges.subscribe {
            viewModel.getPlaces(it.toString())
        }

        disposables += route_layout.routeChanges.subscribe {
            it?.let {
                viewModel.updateJourneyRoutes(it.map { PointInfo(it.place, it.arrivalDate, it.departureDate) })
            }

        }
    }

    override fun provideApp(): TravelsApplication = application as TravelsApplication
}