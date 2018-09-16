package com.travels.android.journeys.create

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.travels.android.base.di.findComponentDependencies
import com.travels.android.base.domain.Response
import com.travels.android.design.widget.Location
import com.travels.android.design.widget.Place
import com.travels.android.design.widget.RouteItem
import com.travels.android.design.widget.RouteLayout
import com.travels.android.journeys.create.di.DaggerCreateNewJourneyComponent
import com.travels.android.journeys.create.util.CreateNewJourneyViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class CreateJourneyActivity : AppCompatActivity() {

    @Inject
    lateinit var createNewJourneyViewModelFactory: CreateNewJourneyViewModelFactory

    val disposables = CompositeDisposable()

    private lateinit var viewModel: CreateNewJourneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCreateNewJourneyComponent.builder().createNewJourneyDependencies(findComponentDependencies()).build().inject(this)
        setContentView(R.layout.activity_create_journey)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val routeLayout = findViewById<RouteLayout>(R.id.route_layout)

        viewModel = ViewModelProviders.of(this, createNewJourneyViewModelFactory)
                .get(CreateNewJourneyViewModel::class.java)
        viewModel.route.observe(this, Observer {
            it?.let {
                routeLayout.setRoutes(it.map { it.toRouteItem() })
            }
        })
        viewModel.places.observe(this, Observer {
            it?.let {
                when (it) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> routeLayout.setPlaces(it.data.map { it.toPlace() })
                    is Response.Failure -> { }
                }
            }
        })
        disposables += routeLayout.placePickerEditTextChanges.subscribe {
            viewModel.getPlaces(it.toString())
        }

        disposables += routeLayout.routeChanges.subscribe {
            it?.let {
                viewModel.updateJourneyRoutes(it.map { it.toRouteItem() })
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_new_journey_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_journey -> {
                viewModel.saveJourney(viewModel.journey.value!!)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

fun createCreateJourneyActivityIntent(context: Context) = Intent(context, CreateJourneyActivity::class.java)


private fun RouteItem.toRouteItem() = com.travels.android.api.journeys.RouteItem("", arrivalDate!!, departureDate!!, place!!.toPlace())

private fun Place.toPlace() = com.travels.android.api.journeys.Place("", title!!, location!!.toLocation())

private fun Location.toLocation() = com.travels.android.api.journeys.Location(lat, lng)

private fun com.travels.android.api.journeys.RouteItem.toRouteItem() = RouteItem(place.toPlace(), arrival, departure)

private fun com.travels.android.api.journeys.Place.toPlace() = Place(title, location.toLocation())

private fun com.travels.android.api.journeys.Location.toLocation() = Location(lat, lng)