package com.travels.android.main.search.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travels.android.base.di.findComponentDependencies
import com.travels.android.base.domain.Response
import com.travels.android.main.search.util.SearchJourneyViewModelFactory
import com.travels.android.main.search.R
import com.travels.android.main.search.di.DaggerSearchJourneyComponent
import javax.inject.Inject

class ListSearchFragment : Fragment() {

    @Inject
    lateinit var searchJourneyViewModelFactory: SearchJourneyViewModelFactory

    private lateinit var viewModel: ListSearchViewModel

    private lateinit var journeysListAdapter: JourneysListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DaggerSearchJourneyComponent
                .builder().searchJourneyDependencies(findComponentDependencies())
                .build()
                .inject(this)

        journeysListAdapter = JourneysListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = journeysListAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, searchJourneyViewModelFactory).get(ListSearchViewModel::class.java)
        viewModel.journeys.observe(this, Observer {
            it?.let {
                when (it) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        val places = it.data.map { it.route }
                        val strings = places.map { it.map { it.place.title }.joinToString() }
                        journeysListAdapter.setItems(strings.map { JourneyItem(it) })
                        journeysListAdapter.notifyDataSetChanged()
                    }
                    is Response.Failure -> {
                    }
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getJourneys()
    }

}
