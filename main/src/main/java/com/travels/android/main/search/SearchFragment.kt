package com.travels.android.main.search

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.travels.android.main.R
import com.travels.android.main.R.id.filters_button
import com.travels.android.main.search.filter.JourneysFilterActivity
import kotlinx.android.synthetic.main.fragment_search.change_search_view_button
import kotlinx.android.synthetic.main.fragment_search.filters_button

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var navController: NavController

    private lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = childFragmentManager.findFragmentById(R.id.fragment_navigation_host_search_view).findNavController()
        change_search_view_button.setOnClickListener {
            if (navController.currentDestination.id == R.id.mapSearchFragment) {
                navController.navigate(R.id.action_mapSearchFragment_to_listSearchFragment)
            } else {
                navController.navigate(R.id.action_listSearchFragment_to_mapSearchFragment)
            }
        }

        filters_button.setOnClickListener {
            val intent = Intent(context, JourneysFilterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

}
