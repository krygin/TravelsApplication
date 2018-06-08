package com.travels.android.main.search.list

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.travels.android.main.R
import kotlinx.android.synthetic.main.list_search_fragment.*

class ListSearchFragment : Fragment() {

    companion object {
        fun newInstance() = ListSearchFragment()
    }

    private lateinit var viewModel: ListSearchViewModel

    private lateinit var journeysListAdapter: JourneysListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        journeysListAdapter = JourneysListAdapter()
        journeysListAdapter.setItems(listOf(
                JourneyItem("Путешествие в Казань"),
                JourneyItem("Путешествие в Амстердам"),
                JourneyItem("Путешествие в Макао"),
                JourneyItem("Путешествие в Сызрань"),
                JourneyItem("Путешествие в Нью-Йорк"),
                JourneyItem("Путешествие в Париж"),
                JourneyItem("Путешествие в Берлин"),
                JourneyItem("Путешествие в Сидней"),
                JourneyItem("Путешествие в Мельбурн"),
                JourneyItem("Путешествие в Рио-де-Жанейро"),
                JourneyItem("Путешествие в Лондон"),
                JourneyItem("Путешествие в Бремен"),
                JourneyItem("Путешествие в Мюнхен")
        )
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = journeysListAdapter
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
