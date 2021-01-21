package com.androidstrike.javit.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstrike.javit.R
import com.androidstrike.javit.interfaces.IRecyclerItemClickListener

class SnippetsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var txt_snippet_name: TextView

    lateinit var iRecyclerItemClickListener: IRecyclerItemClickListener

    fun setClick(iRecyclerItemClickListener: IRecyclerItemClickListener) {
        this.iRecyclerItemClickListener = iRecyclerItemClickListener
    }

    init {
        txt_snippet_name = itemView.findViewById(R.id.tv_snippet_name) as TextView

        itemView.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        iRecyclerItemClickListener.onItemClickListener(v!!, adapterPosition)
    }
}