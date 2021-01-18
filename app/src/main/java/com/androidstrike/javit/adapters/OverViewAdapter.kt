package com.androidstrike.javit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidstrike.javit.R
import com.androidstrike.javit.databinding.RecyclerViewOverviewBinding
import com.androidstrike.javit.models.OverView


class OverViewAdapter : RecyclerView.Adapter<OverViewAdapter.OverViewHolder>() {

    private var overViews: List<OverView>? = null

//    inner class ProfileViewHolder(val binding: CardViewProfileBinding) :
//        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= OverViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_overview,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: OverViewHolder, position: Int) {
        overViews?.let {
            holder.binding.overview = it[position]
            holder.binding.executePendingBindings()
        }    }

    override fun getItemCount()= overViews?.size ?: 0

    fun setOverviews(overviews: List<OverView>) {
        this.overViews = overviews
        notifyDataSetChanged()
    }

    inner class OverViewHolder(val binding: RecyclerViewOverviewBinding) :
        RecyclerView.ViewHolder(binding.root)
    }
