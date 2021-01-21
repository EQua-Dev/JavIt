package com.androidstrike.javit.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstrike.javit.R
import com.androidstrike.javit.interfaces.IRecyclerItemClickListener

class ModulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var txt_module_no: TextView
    var txt_module_name: TextView

    lateinit var iRecyclerItemClickListener: IRecyclerItemClickListener

    fun setClick(iRecyclerItemClickListener: IRecyclerItemClickListener) {
        this.iRecyclerItemClickListener = iRecyclerItemClickListener
    }

    init {
        txt_module_no = itemView.findViewById(R.id.tv_module_no) as TextView
        txt_module_name = itemView.findViewById(R.id.tv_module_name) as TextView

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        iRecyclerItemClickListener.onItemClickListener(v!!, adapterPosition)
    }

}