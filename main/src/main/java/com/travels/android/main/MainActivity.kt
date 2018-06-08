package com.travels.android.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupWithNavController(navigation, supportFragmentManager.findFragmentById(R.id.fragment_navigation_host_main).findNavController())
    }
}
