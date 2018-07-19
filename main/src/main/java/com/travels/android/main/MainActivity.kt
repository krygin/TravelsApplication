package com.travels.android.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation)
        NavigationUI.setupWithNavController(navigationView, supportFragmentManager.findFragmentById(R.id.fragment_navigation_host_main).findNavController())
    }
}
