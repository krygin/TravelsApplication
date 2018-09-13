package com.travels.android.main.search.map

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travels.android.main.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.travels.android.main.search.create.domain.Location
import com.travels.android.main.search.create.domain.Place

class MapSearchFragment : Fragment(), OnMapReadyCallback {
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.google_map_style))
        val polylineOptions = PolylineOptions()
        places.forEach {
            polylineOptions.add(LatLng(it.location.lat, it.location.lng))
        }
        polylineOptions.geodesic(true)
        googleMap?.addPolyline(polylineOptions)

        places.forEach {
            googleMap?.addMarker(MarkerOptions().position(LatLng(it.location.lat, it.location.lng)).title(it.title))
        }
    }

    companion object {
        fun newInstance() = MapSearchFragment()
    }

    private lateinit var viewModel: MapSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.map_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onAttachFragment(childFragment: Fragment?) {
        if (childFragment is SupportMapFragment)
            childFragment.getMapAsync(this)
    }
}

private val places = listOf(
        Place("", "Москва", Location(55.45, 37.37)),
        Place("", "Берлин", Location(52.31, 13.23)),
        Place("", "Париж", Location(48.50, 2.20)),
        Place("", "Стамбул", Location(41.00, 28.57))
)