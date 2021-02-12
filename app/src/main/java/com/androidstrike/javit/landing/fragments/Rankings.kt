package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstrike.javit.R
import com.androidstrike.javit.adapters.ModulesViewHolder
import com.androidstrike.javit.adapters.RankingsViewHolder
import com.androidstrike.javit.models.Modules
import com.androidstrike.javit.models.Ranking
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_rankings.*
import kotlinx.android.synthetic.main.fragment_tutorials.*

class Rankings : Fragment() {

    var adapter: FirebaseRecyclerAdapter<Ranking, RankingsViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rankings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        ranking_list.layoutManager = layoutManager
        ranking_list.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))

        loadRankings()

    }

    private fun loadRankings() {
        val query = FirebaseDatabase.getInstance().getReference("QuestionScore")

        val options = FirebaseRecyclerOptions.Builder<Modules>()
            .setQuery(query, Modules::class.java)
            .build()
    }
}