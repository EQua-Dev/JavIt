package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.androidstrike.javit.adapters.ModulesViewHolder
import com.androidstrike.javit.interfaces.IRecyclerItemClickListener
import com.androidstrike.javit.models.Modules
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_tutorials.*


class Tutorials : Fragment() {

    var adapter: FirebaseRecyclerAdapter<Modules, ModulesViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        Log.d("EQUA", "onActivityCreated: 1")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorials, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        rv_tutorials.layoutManager = layoutManager
        rv_tutorials.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))

        loadModules()

    }

    private fun loadModules() {
        pb_tuts.visibility = View.VISIBLE
        val query = FirebaseDatabase.getInstance().getReference("Modules")

        val options = FirebaseRecyclerOptions.Builder<Modules>()
                .setQuery(query, Modules::class.java)
                .build()

        Log.d("EQUA", "loadModules: query path check")

        adapter = object : FirebaseRecyclerAdapter<Modules, ModulesViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModulesViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_modules, parent, false)
                Log.d("EQUA", "onCreateViewHolder: inflated")
                return ModulesViewHolder(itemView)

            }

            override fun onBindViewHolder(holder: ModulesViewHolder, position: Int, model: Modules) {
                holder.txt_module_no.text = StringBuilder(model.module!!)
                holder.txt_module_name.text = StringBuilder(model.name!!)

                Log.d("EQUA", "onBindViewHolder: holders set")
                pb_tuts.visibility = View.GONE

                holder.setClick(object : IRecyclerItemClickListener {
                    override fun onItemClickListener(view: View, position: Int) {
                        startTutorial(model)
                    }

                })

            }

        }

        adapter!!.startListening()
        rv_tutorials.adapter = adapter
    }

    private fun startTutorial(model: Modules) {

        val youTubePlayerFragment = requireActivity().supportFragmentManager
                .findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment?

        activity?.toast("${model.id + model.name} clicked!! ")
        val modelVideo = model.video_link
        val modelInfo = model.text

        val frag_mod = Module()

        val bundle = Bundle()
        bundle.putString("vidId", modelVideo)
        bundle.putString("mod_txt", modelInfo)
        frag_mod.arguments = bundle


        val manager = fragmentManager

        val frag_transaction = manager?.beginTransaction()

        frag_transaction?.replace(R.id.fragment_container, frag_mod)
        frag_transaction?.commit()
    }

    override fun onStop() {
        if (adapter != null)
            adapter!!.stopListening()
        super.onStop()
    }
}