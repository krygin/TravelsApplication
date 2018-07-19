package com.travels.android.main.search

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

import com.travels.android.main.R
import com.travels.android.main.search.create.CreateNewJourneyActivity
import com.travels.android.main.search.filter.JourneysFilterActivity

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = childFragmentManager.findFragmentById(R.id.fragment_navigation_host_search_view).findNavController()
        view.findViewById<Button>(R.id.change_search_view_button).apply {
            setOnClickListener {
                if (navController.currentDestination.id == R.id.mapSearchFragment) {
                    navController.navigate(R.id.action_mapSearchFragment_to_listSearchFragment)
                } else {
                    navController.navigate(R.id.action_listSearchFragment_to_mapSearchFragment)
                }
            }
        }
        view.findViewById<Button>(R.id.create_new_journey_button).apply {
            setOnClickListener {
                val intent = Intent(context, CreateNewJourneyActivity::class.java)
                startActivityForResult(intent, 2)
            }

        }
        view.findViewById<FloatingActionButton>(R.id.filters_button).apply {
            setOnClickListener {
                val intent = Intent(context, JourneysFilterActivity::class.java)
                startActivityForResult(intent, 1)
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

}
