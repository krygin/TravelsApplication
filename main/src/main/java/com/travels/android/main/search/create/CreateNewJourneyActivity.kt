package com.travels.android.main.search.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.travels.android.main.R
import com.travels.android.main.search.core.Location
import com.travels.android.main.search.core.Place
import com.travels.android.main.search.core.widget.RouteItem
import kotlinx.android.synthetic.main.activity_create_journey.*
import java.util.*

class CreateNewJourneyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journey)
        route_layout.apply {
            addRoute(RouteItem(Place(Location(55.45, 37.37), "Москва"), Date()))
            addRoute(RouteItem(Place(Location(52.31, 13.23), "Берлин"), Date()))
            addRoute(RouteItem(Place(Location(48.50, 2.20), "Париж"), Date()))
            addRoute(RouteItem(Place(Location(41.00, 28.57), "Стамбул"), Date()))
        }
    }
}