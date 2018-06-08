package com.travels.android.main.search.filter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.travels.android.main.R
import kotlinx.android.synthetic.main.activity_journeys_filter.*

class JourneysFilterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journeys_filter)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}