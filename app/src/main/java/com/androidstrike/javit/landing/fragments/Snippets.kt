package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.androidstrike.javit.adapters.ModulesViewHolder
import com.androidstrike.javit.adapters.SnippetsViewHolder
import com.androidstrike.javit.interfaces.IRecyclerItemClickListener
import com.androidstrike.javit.models.Modules
import com.androidstrike.javit.models.Snippet
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_snippets.*
import kotlinx.android.synthetic.main.fragment_tutorials.*

class Snippets : Fragment() {

    var adapter: FirebaseRecyclerAdapter<Snippet, SnippetsViewHolder>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snippets, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        rv_snippets.layoutManager = layoutManager
        rv_snippets.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))

        loadSnippets()
    }

    private fun loadSnippets() {
        pb_snips.visibility = View.VISIBLE
        val query = FirebaseDatabase.getInstance().getReference("Snippets")

        val options = FirebaseRecyclerOptions.Builder<Snippet>()
            .setQuery(query, Snippet::class.java)
            .build()

        Log.d("EQUA", "loadModules: query path check")

        adapter = object : FirebaseRecyclerAdapter<Snippet, SnippetsViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnippetsViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_snippets, parent, false)
                Log.d("EQUA", "onCreateViewHolder: inflated")
                return SnippetsViewHolder(itemView)

            }

            override fun onBindViewHolder(holder: SnippetsViewHolder, position: Int, model: Snippet) {
                holder.txt_snippet_name.text = StringBuilder(model.name!!)

                Log.d("EQUA", "onBindViewHolder: holders set")
                pb_snips.visibility = View.GONE

                holder.setClick(object : IRecyclerItemClickListener {
                    override fun onItemClickListener(view: View, position: Int) {
                        openSnippet(model)
                    }

                })

            }

        }

        adapter!!.startListening()
        rv_snippets.adapter = adapter
    }

    private fun openSnippet(model: Snippet) {
        activity?.toast("${model.name} clicked!! ")
        val snipName = model.name
        val snipText = model.text
        val snipInput = model.input_link
        val snipOutput = model.output_link

        val frag_snip = SnippetCode()

        val bundle = Bundle()
        bundle.putString("snipName", snipName)
        bundle.putString("snipText", snipText)
        bundle.putString("snipIn", snipInput)
        bundle.putString("snipOut", snipOutput)
        frag_snip.arguments = bundle


        val manager = fragmentManager

        val frag_transaction = manager?.beginTransaction()

        frag_transaction?.replace(R.id.fragment_container, frag_snip)
        frag_transaction?.commit()
    }
}