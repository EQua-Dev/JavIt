package com.androidstrike.javit.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstrike.javit.R

class RankingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txt_rank_name: TextView
    var txt_rank_score: TextView

    init {
        txt_rank_name = itemView.findViewById(R.id.txt_ranking_name) as TextView
        txt_rank_score = itemView.findViewById(R.id.txt_ranking_score) as TextView

    }
}